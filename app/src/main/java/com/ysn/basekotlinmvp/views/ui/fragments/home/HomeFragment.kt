package com.ysn.basekotlinmvp.views.ui.fragments.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.basekotlinmvp.R
import com.ysn.basekotlinmvp.di.component.fragments.home.DaggerHomeFragmentComponent
import com.ysn.basekotlinmvp.di.module.fragments.home.HomeFragmentModule
import com.ysn.basekotlinmvp.views.base.BaseFragment
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    lateinit var rootView: View

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityInject() {
        DaggerHomeFragmentComponent.builder()
            .appComponent(getAppComponent())
            .homeFragmentModule(HomeFragmentModule())
            .build()
            .inject(this)
        presenter.attachView(this)
    }

    override fun onError() {
        /* Nothing to do in here */
    }


}
