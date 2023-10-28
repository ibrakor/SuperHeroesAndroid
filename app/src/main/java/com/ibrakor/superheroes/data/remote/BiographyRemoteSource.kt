package com.ibrakor.superheroes.data.remote

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.left
import com.ibrakor.ejercicioformulario02.app.right
import com.ibrakor.superheroes.data.api.SuperHeroApiClient
import com.ibrakor.superheroes.data.api.toModel
import com.ibrakor.superheroes.domain.Biography
import com.ibrakor.superheroes.domain.Work

class BiographyRemoteSource {

    private val apiClient: SuperHeroApiClient = SuperHeroApiClient()
    suspend fun getBiography(heroId: String): Either<ErrorApp, Biography>{
        val biographyResult = apiClient.superHeroApi.getBiographyApi(heroId)
        if (biographyResult.isSuccessful){
            val biography=biographyResult.body()
            return biography!!.toModel().right()
        }
        return ErrorApp.NetworkError.left()
    }
}