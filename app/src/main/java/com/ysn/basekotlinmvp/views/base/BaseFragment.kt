package com.ysn.basekotlinmvp.views.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ysn.basekotlinmvp.di.component.AppComponent
import com.ysn.basekotlinmvp.views.base.mvp.BasePresenter
import com.ysn.basekotlinmvp.views.base.mvp.BaseView

abstract class BaseFragment : Fragment(), BaseView {

    private var presenter: BasePresenter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityInject()
    }

    protected abstract fun onActivityInject()

    fun getAppComponent(): AppComponent = App.appComponent

    override fun setPresenter(presenter: BasePresenter<*>) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        presenter = null
    }

}