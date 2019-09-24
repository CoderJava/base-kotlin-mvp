package com.ysn.basekotlinmvp.views.ui.activities.main

import com.ysn.basekotlinmvp.api.main.MainEndpoints
import com.ysn.basekotlinmvp.storage.sharedpreferences.SharedPreferencesManager
import com.ysn.basekotlinmvp.views.base.mvp.BasePresenter

class MainPresenter(
    private val mainEndpoints: MainEndpoints,
    private val sharedPreferencesManager: SharedPreferencesManager
) : BasePresenter<MainView>() {

}
