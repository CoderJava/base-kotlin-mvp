package com.ysn.basekotlinmvp.views.base.mvp

interface Presenter <V: BaseView> {

    fun attachView(view: V)

    fun detachView()

}