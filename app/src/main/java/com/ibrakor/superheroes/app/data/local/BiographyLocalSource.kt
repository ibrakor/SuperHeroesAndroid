package com.ibrakor.superheroes.app.data.local

import android.content.Context
import com.ibrakor.avilaentapaspractica.app.serialization.JsonSerialization
import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.app.domain.left
import com.ibrakor.superheroes.app.domain.right
import com.ibrakor.superheroes.features.list.domain.Biography
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BiographyLocalSource @Inject constructor(@ApplicationContext private val context: Context, private val jsonSerialization: JsonSerialization){

    val sharedPreferences = context.getSharedPreferences("biographies",Context.MODE_PRIVATE)

    fun getBiography(heroId: String): Either<ErrorApp, Biography> {
        return try {
            val jsonBiography=sharedPreferences.getString(heroId,"")
            if (jsonBiography.isNullOrEmpty()){
                ErrorApp.UnknownError.left()

            } else{
                jsonSerialization.fromJson(jsonBiography, Biography::class.java).right()

            }
        } catch (
            ex: Exception
        ){
            ErrorApp.UnknownError.left()
        }
    }

    fun saveBiography(heroId: String, biography: Biography): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPreferences.edit()){
                val jsonBiography = jsonSerialization.toJson(biography, Biography::class.java)
                putString(heroId,jsonBiography)
                apply()
            }
            return true.right()
        } catch (ex: Exception){
            ErrorApp.UnknownError.left()
        }
    }


}