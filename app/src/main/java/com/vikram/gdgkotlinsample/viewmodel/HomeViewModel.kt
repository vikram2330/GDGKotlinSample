package com.vikram.gdgkotlinsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikram.gdgkotlinsample.repositories.JokeRepository
import kotlinx.coroutines.launch

/**
 * Created by Vikram on 2019-10-10.
 */
class HomeViewModel : ViewModel() {
    private val repo: JokeRepository by lazy { JokeRepository }
    private val categoriesLiveData = MutableLiveData<List<String>>()
    private val stateLiveData = MutableLiveData<State>()

    fun getCategoriesLiveData(): LiveData<List<String>> = categoriesLiveData
    fun getStateLiveData(): LiveData<State> = stateLiveData

    fun getJokeCategories() {
        viewModelScope.launch {
            //showLoader
            stateLiveData.value = LoadingState
            try {
                val categories = repo.getJokeCategories()
                //hideLoader
                stateLiveData.value = SuccessState
                categoriesLiveData.value = categories
            } catch (ex: Exception) {
                //hideLoader
                stateLiveData.value = ErrorState(ex.localizedMessage)
                }
        }
    }

}

sealed class State
object LoadingState : State() //since this does'nt have any param we can make it singleton
object SuccessState : State()//since this does'nt have any param we can make it singleton
class ErrorState(val errorMsg: String?) : State()