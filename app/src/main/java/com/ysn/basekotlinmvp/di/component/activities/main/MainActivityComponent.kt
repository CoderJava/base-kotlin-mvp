package com.ysn.basekotlinmvp.di.component.activities.main

import com.ysn.basekotlinmvp.di.ActivityScope
import com.ysn.basekotlinmvp.di.component.AppComponent
import com.ysn.basekotlinmvp.di.module.activities.main.MainActivityModule
import com.ysn.basekotlinmvp.views.ui.activities.main.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(MainActivityModule::class)])
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

}