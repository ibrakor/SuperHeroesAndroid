package com.ibrakor.superheroes.features.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.superheroes.features.list.domain.GetSuperHeroUseCase
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperHeroDetailViewModel(private val getSuperHeroUseCase: GetSuperHeroUseCase): ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState
    data class UiState(
        val superHero: SuperHeroOutput?=null,
        val errorApp: ErrorApp?=null,
        val isLoading: Boolean=false
    )
    fun loadSuperHeroDetail(heroId: Int){
        _uiState.value= (UiState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            getSuperHeroUseCase.invoke(heroId).fold(
                {responseError(it)},{responseGetSuperHeroDetailSucces(it)}
            )
        }
    }
    private fun responseGetSuperHeroDetailSucces(superHero: SuperHeroOutput){
        _uiState.postValue(UiState(superHero = superHero, isLoading = false))
    }

    private fun responseError(it: ErrorApp) {
        _uiState.postValue(UiState(errorApp = it, isLoading = false))
    }
}