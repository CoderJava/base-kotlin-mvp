package com.ysn.basekotlinmvp.di.module.activities.main

import com.ysn.basekotlinmvp.api.main.MainEndpoints
import com.ysn.basekotlinmvp.di.ActivityScope
import com.ysn.basekotlinmvp.storage.sharedpreferences.SharedPreferencesManager
import com.ysn.basekotlinmvp.views.ui.activities.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    internal fun providesMainPresenter(
        mainEndpoints: MainEndpoints,
        sharedPreferencesManager: SharedPreferencesManager
    ): MainPresenter = MainPresenter(mainEndpoints, sharedPreferencesManager)

}