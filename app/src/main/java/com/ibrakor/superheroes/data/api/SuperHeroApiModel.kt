package com.ibrakor.superheroes.data.api

import com.google.gson.annotations.SerializedName
import com.ibrakor.superheroes.domain.Biography
import com.ibrakor.superheroes.domain.SuperHero
import com.ibrakor.superheroes.domain.Work


data class SuperHeroApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("biography") var biography: BiographyApiModel,
    @SerializedName("images") val images: ImagesApiModel,
    @SerializedName("work") var work: WorkApiModel

)

fun SuperHeroApiModel.toModel(): SuperHero = SuperHero(this.id,this.name,this.images.toUrl())

data class BiographyApiModel(
    @SerializedName("fullName") val fullName: String
)
fun BiographyApiModel.toModel(): Biography = Biography(this.fullName)
data class ImagesApiModel(
    @SerializedName("sm") val urlImage: String
)
fun ImagesApiModel.toUrl(): String= (this.urlImage)
data class WorkApiModel(
    @SerializedName("occupation") val occupation: String
)
fun WorkApiModel.toModel(): Work = Work(this.occupation)