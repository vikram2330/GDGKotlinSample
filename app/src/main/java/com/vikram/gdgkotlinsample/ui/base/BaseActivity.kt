package com.vikram.gdgkotlinsample.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

/**
 * Created by Vikram on 2019-10-10.
 */
abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : FragmentActivity() {

    protected lateinit var binding: B
    lateinit var viewModel: VM
    abstract fun getViewModelClass(): Class<VM>
    @LayoutRes
    protected abstract fun layoutId(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId())
        viewModel = ViewModelProviders.of(this)[getViewModelClass()]
    }

}