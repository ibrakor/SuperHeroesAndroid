package com.ibrakor.superheroes.app.data.remote

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.left
import com.ibrakor.ejercicioformulario02.app.right
import com.ibrakor.superheroes.app.data.remote.api.SuperHeroApiService
import com.ibrakor.superheroes.app.data.remote.api.apiCall
import com.ibrakor.superheroes.app.data.remote.api.toModel
import com.ibrakor.superheroes.features.list.domain.SuperHero
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class SuperHeroesRemoteSource @Inject constructor(private val apiService: SuperHeroApiService) {

    suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHero>> {
        return apiCall {
            apiService.getSuperHero()
        }.map {
            it.toModel()
        }
    }
}