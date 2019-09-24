package com.ysn.basekotlinmvp.di.component.fragments.home

import com.ysn.basekotlinmvp.di.FragmentScope
import com.ysn.basekotlinmvp.di.component.AppComponent
import com.ysn.basekotlinmvp.di.module.fragments.home.HomeFragmentModule
import com.ysn.basekotlinmvp.views.ui.fragments.home.HomeFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(HomeFragmentModule::class)])
interface HomeFragmentComponent {

    fun inject(homeFragment: HomeFragment)

}