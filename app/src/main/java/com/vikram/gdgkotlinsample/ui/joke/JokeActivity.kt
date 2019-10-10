package com.vikram.gdgkotlinsample.ui.joke

import com.vikram.gdgkotlinsample.R
import com.vikram.gdgkotlinsample.databinding.ActivityJokeBinding
import com.vikram.gdgkotlinsample.ui.base.BaseActivity


class JokeActivity : BaseActivity<ActivityJokeBinding, JokeViewModel>() {
    override fun getViewModelClass(): Class<JokeViewModel> = JokeViewModel::class.java
    override fun layoutId(): Int = R.layout.activity_joke
}