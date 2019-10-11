package com.vikram.gdgkotlinsample.ui.joke

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.vikram.gdgkotlinsample.R
import com.vikram.gdgkotlinsample.databinding.ActivityJokeBinding
import com.vikram.gdgkotlinsample.ui.base.BaseActivity
import com.vikram.gdgkotlinsample.ui.extensions.loadImageForUrl
import com.vikram.gdgkotlinsample.ui.extensions.setVisible
import com.vikram.gdgkotlinsample.viewmodel.ErrorState
import com.vikram.gdgkotlinsample.viewmodel.LoadingState
import com.vikram.gdgkotlinsample.viewmodel.SuccessState


class JokeActivity : BaseActivity<ActivityJokeBinding, JokeViewModel>() {
    companion object {
        private const val KEY_JOKE_CATEGORY = "KEY_JOKE_CATEGORY"
        fun getLaunchIntent(context: Context, category: String): Intent {
            return Intent(context, JokeActivity::class.java).apply {
                val bundle = bundleOf(
                    KEY_JOKE_CATEGORY to category
                )
                putExtras(bundle)
            }
        }
    }

    override fun getViewModelClass(): Class<JokeViewModel> = JokeViewModel::class.java
    override fun layoutId(): Int = R.layout.activity_joke

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()

        val jokeCategory = intent.getStringExtra(KEY_JOKE_CATEGORY)
        viewModel.onViewReady(jokeCategory)
    }

    private fun initObservers() {
        viewModel.getJokeLiveData().observe(this, Observer {
            it?.let { joke ->
                binding.txtJoke.text = joke.value
                binding.imgJoke.loadImageForUrl(joke.iconUrl)
            }
        })

        viewModel.getStateLiveData().observe(this, Observer { state ->
            when (state) {
                is LoadingState -> {
                    binding.progressCircular.show()
                    binding.txtJoke.setVisible(false)
                    binding.imgJoke.setVisible(false)
                }
                is SuccessState -> {
                    binding.progressCircular.hide()
                    binding.txtJoke.setVisible(true)
                    binding.imgJoke.setVisible(true)
                }
                is ErrorState -> {
                    binding.progressCircular.hide()
                    showSnackBar(state.errorMsg ?: getString(R.string.error_msg))
                }
            }
        })
    }
}