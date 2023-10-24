package com.ibrakor.superheroes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.superheroes.domain.GetSuperHeroUseCase
import com.ibrakor.superheroes.domain.GetSuperHeroesFeedUseCase
import com.ibrakor.superheroes.domain.SuperHero
import com.ibrakor.superheroes.domain.SuperHeroOutput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperHeroViewModel(private val getSuperHeroUseCase: GetSuperHeroUseCase, private val getSuperHeroesFeedUseCase: GetSuperHeroesFeedUseCase): ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState
    data class UiState(
        val errorApp: ErrorApp?=null,
        val isLoading: Boolean=false,
        val superHero: SuperHeroOutput?=null,
        val superHeroList: List<SuperHeroOutput>?=null
    )

    fun loadSuperHero(id: Int){
        //_uiState.postValue(UiState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            getSuperHeroUseCase.invoke(id).fold(
                {responseError(it)},{ResponseGetSuperHeroSucces(it)}
            )
        }
    }

    private fun ResponseGetSuperHeroSucces(it: SuperHeroOutput) {
        _uiState.postValue(UiState(superHero = it))
    }

    private fun responseError(it: ErrorApp) {
        _uiState.postValue(UiState(errorApp = it))
    }
}