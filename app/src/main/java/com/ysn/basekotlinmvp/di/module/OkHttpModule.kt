package com.ysn.basekotlinmvp.di.module

import android.app.Application
import android.content.Context
import com.ysn.basekotlinmvp.BuildConfig
import com.ysn.basekotlinmvp.storage.sharedpreferences.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class OkHttpModule {

    private fun getBaseBuilder(cache: Cache): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .cache(cache)
            .retryOnConnectionFailure(true)
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
    }

    class CachingControlInterceptor(private val sharedPreferencesManager: SharedPreferencesManager) :
        Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()
            val cacheControl = CacheControl.Builder()
                .maxStale(1, TimeUnit.MINUTES)
                .maxAge(1, TimeUnit.MINUTES)
                .build()
            requestBuilder.cacheControl(cacheControl)
            if (sharedPreferencesManager.isKeyExists(SharedPreferencesManager.accessToken)) {
                requestBuilder.header(
                    "Authorization",
                    "Bearer ${sharedPreferencesManager.getDataString(SharedPreferencesManager.accessToken)}"
                )
            }
            val request = requestBuilder.build()
            val originalResponse = chain.proceed(request)
            return originalResponse.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, only-if-cached, max-stale=604800")
                .build()
        }

    }

    class TokenAuthenticator(
        private val context: Context,
        private val sharedPreferencesManager: SharedPreferencesManager
    ) : Authenticator {

        override fun authenticate(route: Route, response: Response): Request? {
            val urlRefreshToken = BuildConfig.BASE_URL_ENDPOINT + "refresh"
            val userid =
                sharedPreferencesManager.getDataString(SharedPreferencesManager.phoneNumberLogin)!!
            var refreshToken =
                sharedPreferencesManager.getDataString(SharedPreferencesManager.refreshToken)!!
            val userType =
                sharedPreferencesManager.getDataString(SharedPreferencesManager.userType)!!
            try {
                val jsonObjectBody = JSONObject()
                jsonObjectBody.put("user_id", userid)
                jsonObjectBody.put("user_type", userType)
                jsonObjectBody.put("refresh_token", refreshToken)
                val request = Request.Builder()
                    .url(urlRefreshToken)
                    .post(
                        RequestBody.create(
                            MediaType.parse("application/json;charset=utf-8"),
                            jsonObjectBody.toString()
                        )
                    )
                    .build()
                val responseRefreshToken = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
                    .newCall(request)
                    .execute()
                val isSuccessful = responseRefreshToken.isSuccessful
                val responseCode = responseRefreshToken.code()
                return if (isSuccessful && responseCode == 200) {
                    val strResponseBody = responseRefreshToken.body()!!.string()
                    val jsonObjectResponse = JSONObject(strResponseBody)
                    val accessToken = jsonObjectResponse.getJSONObject("session").getString("id")
                    refreshToken = jsonObjectResponse.getJSONObject("session_refresh")
                        .getString("refresh_token")
                    sharedPreferencesManager.putDataString(
                        SharedPreferencesManager.accessToken,
                        accessToken
                    )
                    sharedPreferencesManager.putDataString(
                        SharedPreferencesManager.refreshToken,
                        refreshToken
                    )
                    response.request()
                        .newBuilder()
                        .header("Authorization", "Bearer $accessToken")
                        .build()
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
        }

    }

    @Provides
    @Singleton
    fun providesOkHttpCache(application: Application): Cache =
        Cache(application.cacheDir, 10 * 1024 * 1024)

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun providesCachingControlInterceptor(sharedPreferencesManager: SharedPreferencesManager): CachingControlInterceptor =
        CachingControlInterceptor(sharedPreferencesManager)

    @Provides
    @Singleton
    fun providesTokenAuthenticator(
        application: Application,
        sharedPreferencesManager: SharedPreferencesManager
    ): TokenAuthenticator = TokenAuthenticator(application, sharedPreferencesManager)

    @Provides
    @Singleton
    fun providesOkHttp(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor,
        cachingControlInterceptor: CachingControlInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient = getBaseBuilder(cache)
        .addNetworkInterceptor(cachingControlInterceptor)
        .addInterceptor(loggingInterceptor)
        .authenticator(tokenAuthenticator)
        .build()

}