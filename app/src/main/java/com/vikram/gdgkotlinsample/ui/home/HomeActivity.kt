package com.vikram.gdgkotlinsample.ui.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vikram.gdgkotlinsample.R
import com.vikram.gdgkotlinsample.databinding.HomeActivityBinding
import com.vikram.gdgkotlinsample.ui.base.BaseActivity
import com.vikram.gdgkotlinsample.viewmodel.HomeViewModel

/**
 * Created by Vikram on 2019-10-10.
 */
class HomeActivity : BaseActivity<HomeActivityBinding, HomeViewModel>(),
    JokeCategoriesAdapter.InteractionListener {
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun layoutId(): Int = R.layout.home_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding.rvJokeCategories) {
            layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.VERTICAL, false)
            adapter = JokeCategoriesAdapter(this@HomeActivity)
        }
    }

    override fun onItemSelected(position: Int, item: String) {

    }
}