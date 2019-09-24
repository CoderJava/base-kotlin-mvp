package com.ysn.basekotlinmvp.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ysn.basekotlinmvp.storage.sharedpreferences.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(SharedPreferencesManager.prefData, Context.MODE_PRIVATE)

}