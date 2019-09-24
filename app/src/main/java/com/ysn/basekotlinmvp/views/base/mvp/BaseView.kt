package com.ysn.basekotlinmvp.views.base.mvp

interface BaseView {

    fun onError()

    fun setPresenter(presenter: BasePresenter<*>)

}