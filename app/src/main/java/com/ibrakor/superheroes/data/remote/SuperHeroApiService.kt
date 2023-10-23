package com.ibrakor.superheroes.data.remote

import com.ibrakor.superheroes.data.api.BiographyApiModel
import com.ibrakor.superheroes.data.api.WorkApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroApiService {


    @GET("biography/{superHeroId}.json")
    suspend fun getBiographyApi(@Path("superHeroId") superHeroId: String): Response<BiographyApiModel>

    @GET("work/{superHeroId}.json")
    suspend fun getWorkApi(@Path("superHeroId") superHeroId: String): Response<WorkApiModel>
}