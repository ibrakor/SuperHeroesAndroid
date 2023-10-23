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
            var heroes=heroesResult.body()
            for (i in 0..4){
                val superHeroId = heroes!!.get(i).id.toString()
                val workResult = apiClient.superHeroApi.getWorkApi(superHeroId)
                val biographyResult = apiClient.superHeroApi.getBiographyApi(superHeroId)
                heroes[i].work= workResult.body()!!
                heroes[i].biography= biographyResult.body()!!
            }
            return heroes!!.toModel().right()
        }
        return ErrorApp.NetworkError.left()

    }
}