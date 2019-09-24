package com.ysn.basekotlinmvp.di.module.api.main

import com.ysn.basekotlinmvp.api.main.MainEndpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class MainApiModule {

    @Provides
    @Singleton
    fun providesEndpoints(@Named("main") retrofit: Retrofit): MainEndpoints = retrofit.create(MainEndpoints::class.java)

}