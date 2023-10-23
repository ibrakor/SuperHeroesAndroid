package com.ibrakor.superheroes.data.api

import com.google.gson.annotations.SerializedName


data class SuperHeroApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("biography") val biography: BiographyApiModel,
    @SerializedName("images") val images: ImagesApiModel,
    @SerializedName("work") val work: WorkApiModel

)
data class BiographyApiModel(
    @SerializedName("fullName") val fullName: String
)
data class ImagesApiModel(
    @SerializedName("sm") val urlImage: String
)
data class WorkApiModel(
    @SerializedName("occupation") val occupation: String
)