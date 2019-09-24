package com.ysn.basekotlinmvp.views.base

import android.app.Application
import com.facebook.stetho.Stetho
import com.ysn.basekotlinmvp.di.component.AppComponent
import com.ysn.basekotlinmvp.di.component.DaggerAppComponent
import com.ysn.basekotlinmvp.di.module.AppModule

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initStetho()
        initDagger()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}