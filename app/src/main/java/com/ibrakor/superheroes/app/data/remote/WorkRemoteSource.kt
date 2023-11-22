package com.ibrakor.superheroes.app.data.remote

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.left
import com.ibrakor.ejercicioformulario02.app.right
import com.ibrakor.superheroes.app.data.remote.api.SuperHeroApiService
import com.ibrakor.superheroes.app.data.remote.api.apiCall
import com.ibrakor.superheroes.app.data.remote.api.toModel
import com.ibrakor.superheroes.features.list.domain.Work
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class WorkRemoteSource @Inject constructor(private val apiService: SuperHeroApiService) {

    suspend fun getWork(heroId: String): Either<ErrorApp, Work> {
        return apiCall {
            apiService.getWorkApi(heroId)
        }.map {
            it.toModel()
        }
    }
}