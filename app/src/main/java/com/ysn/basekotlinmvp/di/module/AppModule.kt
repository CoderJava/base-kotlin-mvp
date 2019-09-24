package com.ysn.basekotlinmvp.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication() = application

    /*@Provides
    @Singleton
    fun providesFirebaseDatabase() = FirebaseDatabase.getInstance()*/

}