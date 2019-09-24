package com.ysn.basekotlinmvp.di.module.fragments.home

import com.ysn.basekotlinmvp.api.main.MainEndpoints
import com.ysn.basekotlinmvp.di.FragmentScope
import com.ysn.basekotlinmvp.views.ui.fragments.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentModule {

    @Provides
    @FragmentScope
    internal fun providesHomePresenter(mainEndpoints: MainEndpoints): HomePresenter =
        HomePresenter(mainEndpoints)

}