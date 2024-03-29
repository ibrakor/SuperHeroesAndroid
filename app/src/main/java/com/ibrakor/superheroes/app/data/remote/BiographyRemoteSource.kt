package com.ibrakor.superheroes.app.data.remote

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.app.data.remote.api.SuperHeroApiService
import com.ibrakor.superheroes.app.data.remote.api.apiCall
import com.ibrakor.superheroes.app.data.remote.api.toModel
import com.ibrakor.superheroes.features.list.domain.Biography
import javax.inject.Inject

class BiographyRemoteSource @Inject constructor(private val apiService: SuperHeroApiService) {

    suspend fun getBiography(heroId: String): Either<ErrorApp, Biography> {
        return apiCall {
            apiService.getBiographyApi(heroId)
        }.map {
            it.toModel()
        }
    }
}