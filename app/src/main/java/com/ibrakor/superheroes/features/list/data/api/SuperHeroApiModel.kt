package com.ibrakor.superheroes.features.list.data.api

import com.google.gson.annotations.SerializedName
import com.ibrakor.superheroes.features.detail.domain.Images
import com.ibrakor.superheroes.features.detail.domain.PowerStats
import com.ibrakor.superheroes.features.list.domain.Biography
import com.ibrakor.superheroes.features.list.domain.SuperHero
import com.ibrakor.superheroes.features.list.domain.Work


data class SuperHeroApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("images") val images: ImagesApiModel,
    @SerializedName("work") var work: WorkApiModel,
    @SerializedName("connections") val conections: ConnectionsApiModel,
    @SerializedName("powerstats") val powerstats: PowerStatsApiModel

)

fun SuperHeroApiModel.toModel(): SuperHero = SuperHero(this.id,this.name,this.images.toModel(),this.conections.affiliation, this.powerstats.toModel())

data class BiographyApiModel(
    @SerializedName("fullName") val fullName: String,
    @SerializedName("alignment") val alignment: String
)
fun BiographyApiModel.toModel(): Biography = Biography(this.fullName,this.alignment)
data class ImagesApiModel(
    @SerializedName("xs") val xsurlImage: String,
    @SerializedName("sm") val smUrlImage: String,
    @SerializedName("md") val mdUrlImage: String,
    @SerializedName("lg") val lgUrlImage: String
)
fun ImagesApiModel.toModel(): Images = Images(this.xsurlImage,this.smUrlImage,this.mdUrlImage,this.lgUrlImage)

data class WorkApiModel(
    @SerializedName("occupation") val occupation: String
)
data class ConnectionsApiModel(
    @SerializedName("groupAffiliation") val affiliation: String
)
fun WorkApiModel.toModel(): Work = Work(this.occupation)

data class PowerStatsApiModel(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("combat") val combat: String
)
fun PowerStatsApiModel.toModel(): PowerStats = PowerStats(this.intelligence, this.speed,this.combat)