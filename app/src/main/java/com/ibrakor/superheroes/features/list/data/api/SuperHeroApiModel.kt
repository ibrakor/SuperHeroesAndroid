package com.ibrakor.superheroes.features.list.data.api

import com.google.gson.annotations.SerializedName
import com.ibrakor.superheroes.features.list.domain.Biography
import com.ibrakor.superheroes.features.list.domain.SuperHero
import com.ibrakor.superheroes.features.list.domain.Work


data class SuperHeroApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("images") val images: ImagesApiModel,
    @SerializedName("work") var work: WorkApiModel,
    @SerializedName("connections") val conections: ConnectionsApiMode

)

fun SuperHeroApiModel.toModel(): SuperHero = SuperHero(this.id,this.name,this.images.toUrl(),this.conections.affiliation)

data class BiographyApiModel(
    @SerializedName("fullName") val fullName: String,
    @SerializedName("alignment") val alignment: String
)
fun BiographyApiModel.toModel(): Biography = Biography(this.fullName,this.alignment)
data class ImagesApiModel(
    @SerializedName("lg") val urlImage: String
)
fun ImagesApiModel.toUrl(): String= (this.urlImage)
data class WorkApiModel(
    @SerializedName("occupation") val occupation: String
)
data class ConnectionsApiMode(
    @SerializedName("groupAffiliation") val affiliation: String
)
fun WorkApiModel.toModel(): Work = Work(this.occupation)