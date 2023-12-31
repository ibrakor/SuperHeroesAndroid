package com.ibrakor.superheroes.features.list.data.remote

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.left
import com.ibrakor.ejercicioformulario02.app.right
import com.ibrakor.superheroes.features.list.data.api.SuperHeroApiClient
import com.ibrakor.superheroes.features.list.data.api.toModel
import com.ibrakor.superheroes.features.list.domain.Biography
import com.ibrakor.superheroes.features.list.domain.Work
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class BiographyRemoteSource {

    private val apiClient: SuperHeroApiClient = SuperHeroApiClient()
    suspend fun getBiography(heroId: String): Either<ErrorApp, Biography>{
        try {
            val biographyResult = apiClient.superHeroApi.getBiographyApi(heroId)
            if (biographyResult.isSuccessful){
                val biography=biographyResult.body()
                return biography!!.toModel().right()
        }
            return ErrorApp.NetworkError.left()

        } catch (ex: TimeoutException){
            return ErrorApp.NetworkError.left()
        } catch (ex: UnknownHostException){
            return ErrorApp.NetworkError.left()
        } catch (ex: ConnectException) {
            return ErrorApp.NetworkError.left()
        } catch (ex: Exception){
            return ErrorApp.UnknownError.left()
        }

    }
}