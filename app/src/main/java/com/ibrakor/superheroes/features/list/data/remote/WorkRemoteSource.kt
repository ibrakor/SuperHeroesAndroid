package com.ibrakor.superheroes.features.list.data.remote

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.left
import com.ibrakor.ejercicioformulario02.app.right
import com.ibrakor.superheroes.features.list.data.api.SuperHeroApiClient
import com.ibrakor.superheroes.features.list.data.api.toModel
import com.ibrakor.superheroes.features.list.domain.Work
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class WorkRemoteSource {
    private val apiClient: SuperHeroApiClient = SuperHeroApiClient()

    suspend fun getWork(heroId: String): Either<ErrorApp, Work>{
        try {
            val workResult = apiClient.superHeroApi.getWorkApi(heroId)
            if (workResult.isSuccessful){
                val work=workResult.body()
                return work!!.toModel().right()
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