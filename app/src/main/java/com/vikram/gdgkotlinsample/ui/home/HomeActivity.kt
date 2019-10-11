package com.vikram.gdgkotlinsample.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vikram.gdgkotlinsample.R
import com.vikram.gdgkotlinsample.databinding.HomeActivityBinding
import com.vikram.gdgkotlinsample.ui.base.BaseActivity
import com.vikram.gdgkotlinsample.ui.joke.JokeActivity
import com.vikram.gdgkotlinsample.viewmodel.ErrorState
import com.vikram.gdgkotlinsample.viewmodel.HomeViewModel
import com.vikram.gdgkotlinsample.viewmodel.LoadingState
import com.vikram.gdgkotlinsample.viewmodel.SuccessState

/**
 * Created by Vikram on 2019-10-10.
 */
class HomeActivity : BaseActivity<HomeActivityBinding, HomeViewModel>(),
    JokeCategoriesAdapter.InteractionListener {
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun layoutId(): Int = R.layout.home_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        initObservers()
        viewModel.getJokeCategories()
    }

    private fun initObservers() {
        viewModel.getCategoriesLiveData().observe(this, Observer {
            it?.let { categories ->
                binding.rvJokeCategories.setData(categories)
            }
        })
        viewModel.getStateLiveData().observe(this, Observer { state ->
            when (state) {
                is LoadingState -> binding.progressCircular.show()
                is SuccessState -> binding.progressCircular.hide()
                is ErrorState -> {
                    binding.progressCircular.hide()
                    showSnackBar(state.errorMsg ?: getString(R.string.error_msg))
                }
            }
        })
    }

    private fun initUI() {
        with(binding.rvJokeCategories) {
            layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.VERTICAL, false)
            adapter = JokeCategoriesAdapter(this@HomeActivity)
        }
    }

    private fun RecyclerView.setData(categories: List<String>) {
        (adapter as JokeCategoriesAdapter).submitList(categories)
    }

    override fun onCategorySelected(position: Int, category: String) {
        val intent = JokeActivity.getLaunchIntent(this, category)
        startActivity(intent)
    }
}