package com.ibrakor.superheroes.data.remote

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.left
import com.ibrakor.ejercicioformulario02.app.right
import com.ibrakor.superheroes.app.extensions.toModel
import com.ibrakor.superheroes.data.api.SuperHeroApiClient
import com.ibrakor.superheroes.domain.SuperHero

class SuperHeroesRemoteSource {

    private val apiClient:SuperHeroApiClient= SuperHeroApiClient()
    suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHero>>{
        val heroesResult = apiClient.superHeroApi.getSuperHero()
        if (heroesResult.isSuccessful){
            val heroes=heroesResult.body()!!.subList(0, 20)

            return heroes!!.toModel().right()
        }
        return ErrorApp.NetworkError.left()

    }
}