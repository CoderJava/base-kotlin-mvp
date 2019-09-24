package com.ysn.basekotlinmvp.views.ui.activities.main

import android.os.Bundle
import com.ysn.basekotlinmvp.R
import com.ysn.basekotlinmvp.di.component.activities.main.DaggerMainActivityComponent
import com.ysn.basekotlinmvp.di.module.activities.main.MainActivityModule
import com.ysn.basekotlinmvp.views.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityInject() {
        DaggerMainActivityComponent.builder()
            .appComponent(getAppComponent())
            .mainActivityModule(MainActivityModule())
            .build()
            .inject(this)
        presenter.attachView(this)
    }

    override fun onError() {
        /* Nothing to do in here */
    }

}
