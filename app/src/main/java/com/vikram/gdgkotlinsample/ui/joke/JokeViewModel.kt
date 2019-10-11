package com.vikram.gdgkotlinsample.ui.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikram.gdgkotlinsample.models.Joke
import com.vikram.gdgkotlinsample.repositories.JokeRepository
import com.vikram.gdgkotlinsample.viewmodel.ErrorState
import com.vikram.gdgkotlinsample.viewmodel.LoadingState
import com.vikram.gdgkotlinsample.viewmodel.State
import com.vikram.gdgkotlinsample.viewmodel.SuccessState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class JokeViewModel: ViewModel() {
    private val repo: JokeRepository by lazy { JokeRepository }
    private val stateLiveData = MutableLiveData<State>()
    private val jokeOfCategory = MutableLiveData<Joke>()

    fun getJokeLiveData(): LiveData<Joke> = jokeOfCategory
    fun getStateLiveData(): LiveData<State> = stateLiveData

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        stateLiveData.value = ErrorState(throwable.localizedMessage)
    }

    /**
     * Called when view is ready with joke category
     *
     * @param jokeCategory
     */
    fun onViewReady(jokeCategory: String? = null) {
        jokeCategory?.let { category ->
            fetchJokeForCategory(category)
        } ?: run {
            stateLiveData.value = ErrorState("Joke category is NULL")
        }
    }

    private fun fetchJokeForCategory(category: String) {
        viewModelScope.launch(errorHandler) {
            stateLiveData.value = LoadingState
            repo.getRandomJokeForCategory(category)?.let {
                stateLiveData.value = SuccessState
                jokeOfCategory.value = it
            }
        }
    }
}


