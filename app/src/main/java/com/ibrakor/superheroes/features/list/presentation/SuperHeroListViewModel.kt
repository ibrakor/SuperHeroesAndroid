package com.ibrakor.superheroes.features.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.features.list.domain.GetSuperHeroesFeedUseCase
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperHeroListViewModel @Inject constructor(private val getSuperHeroesFeedUseCase: GetSuperHeroesFeedUseCase): ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState
    data class UiState(
        val errorApp: ErrorApp?=null,
        val isLoading: Boolean=false,
        val superHeroList: List<SuperHeroOutput>?=null
    )

    fun loadSuperHerosList(){
        _uiState.value= (UiState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            getSuperHeroesFeedUseCase.invoke().fold(
                {responseError(it)},{responseGetAllSuperHeroesSucces(it)}
            )
        }
    }

    private fun responseGetAllSuperHeroesSucces(superHeroList: List<SuperHeroOutput>){
        _uiState.postValue(UiState(superHeroList = superHeroList, isLoading = false))
    }

    private fun responseError(it: ErrorApp) {
        _uiState.postValue(UiState(errorApp = it))
    }
}