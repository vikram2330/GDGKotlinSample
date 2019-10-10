package com.vikram.gdgkotlinsample.ui.home

import android.os.Bundle
import com.vikram.gdgkotlinsample.R
import com.vikram.gdgkotlinsample.databinding.HomeActivityBinding
import com.vikram.gdgkotlinsample.ui.base.BaseActivity
import com.vikram.gdgkotlinsample.viewmodel.HomeViewModel

/**
 * Created by Vikram on 2019-10-10.
 */
class HomeActivity: BaseActivity<HomeActivityBinding, HomeViewModel>() {


    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun layoutId(): Int = R.layout.home_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}