package com.ibrakor.superheroes.features.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.features.account.domain.AccountModel
import com.ibrakor.superheroes.features.account.domain.GetAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getAccountUseCase: GetAccountUseCase) :
    ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun getAccount() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getAccountUseCase.invoke().fold(
                { responseError(it) }, { responseSuccess(it) }
            )
        }
    }

    private fun responseSuccess(it: AccountModel?) {
        _uiState.postValue(UiState(account = it))
    }

    private fun responseError(it: ErrorApp) {
        _uiState.postValue(UiState(error = it))
    }

    data class UiState(
        val error: ErrorApp? = null,
        val account: AccountModel? = null,
        val isLoading: Boolean = false
    )
}