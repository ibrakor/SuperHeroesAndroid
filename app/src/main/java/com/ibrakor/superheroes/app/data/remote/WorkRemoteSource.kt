package com.ibrakor.superheroes.app.data.remote

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.app.data.remote.api.SuperHeroApiService
import com.ibrakor.superheroes.app.data.remote.api.apiCall
import com.ibrakor.superheroes.app.data.remote.api.toModel
import com.ibrakor.superheroes.features.list.domain.Work
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