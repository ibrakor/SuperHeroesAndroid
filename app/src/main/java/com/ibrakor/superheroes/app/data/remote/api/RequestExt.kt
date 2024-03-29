package com.ibrakor.superheroes.app.data.remote.api

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.app.domain.left
import com.ibrakor.superheroes.app.domain.right
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T: Any> apiCall(call: suspend () -> Response<T>): Either<ErrorApp, T> {
    val response : Response<T>
    try {
        response = call.invoke()
    } catch (ex: Throwable){
        return when(ex){
            is ConnectException -> ErrorApp.UnknownError.left()
            is UnknownHostException -> ErrorApp.UnknownError.left()
            is SocketTimeoutException -> ErrorApp.UnknownError.left()
            else -> ErrorApp.UnknownError.left()
        }
    }
    if (!response.isSuccessful){
        return ErrorApp.UnknownError.left()
    }
    return response.body()!!.right()
}