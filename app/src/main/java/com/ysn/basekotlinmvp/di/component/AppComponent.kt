package com.ysn.basekotlinmvp.di.component

import android.app.Application
import android.content.SharedPreferences
import com.ysn.basekotlinmvp.api.main.MainEndpoints
import com.ysn.basekotlinmvp.di.module.*
import com.ysn.basekotlinmvp.di.module.api.main.MainApiModule
import com.ysn.basekotlinmvp.storage.database.dao.namatable.NamaTableDao
import dagger.Component
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AppModule::class), (RetrofitModule::class), (MainApiModule::class),
        (OkHttpModule::class), (SharedPreferencesModule::class), (AppDatabaseModule::class)]
)
interface AppComponent {

    fun application(): Application

    @Named("main")
    fun retrofitMain(): Retrofit

    fun mainEndpoints(): MainEndpoints

    fun cache(): Cache

    fun client(): OkHttpClient

    fun loggingInterceptor(): HttpLoggingInterceptor

    fun sharedPreferences(): SharedPreferences

    fun namaTableDao(): NamaTableDao

}