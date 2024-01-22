package com.ibrakor.superheroes.app.data.remote

import com.ibrakor.superheroes.app.data.remote.api.SuperHeroApiService
import com.ibrakor.superheroes.app.data.remote.api.apiCall
import com.ibrakor.superheroes.app.data.remote.api.toModel
import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.features.list.domain.SuperHero
import javax.inject.Inject

class SuperHeroesRemoteSource @Inject constructor(private val apiService: SuperHeroApiService) {

    suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHero>> {
        return apiCall {
            apiService.getSuperHero()
        }.map { superheroesList ->
            superheroesList.subList(0, 100).toModel()
        }
    }
}