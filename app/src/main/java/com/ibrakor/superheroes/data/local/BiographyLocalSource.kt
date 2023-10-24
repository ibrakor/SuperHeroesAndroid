package com.ibrakor.superheroes.data.local

import android.content.Context
import com.ibrakor.avilaentapaspractica.app.serialization.JsonSerialization
import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.left
import com.ibrakor.ejercicioformulario02.app.right
import com.ibrakor.superheroes.domain.Biography

class BiographyLocalSource (private val context: Context, private val jsonSerialization: JsonSerialization){

    val sharedPreferences = context.getSharedPreferences("biographies",Context.MODE_PRIVATE)

    fun getBiography(heroId: String): Either<ErrorApp, Biography>{
        return try {
            val jsonBiography=sharedPreferences.getString(heroId,"")
            jsonSerialization.fromJson(jsonBiography!!,Biography::class.java).right()
        } catch (
            ex: Exception
        ){
            ErrorApp.UnknownError.left()
        }
    }

    fun saveBiography(heroId: String, biography: Biography): Either<ErrorApp, Boolean>{
        return try {
            with(sharedPreferences.edit()){
                val jsonBiography = jsonSerialization.toJson(biography,Biography::class.java)
                putString(heroId,jsonBiography)
                apply()
            }
            return true.right()
        } catch (ex: Exception){
            ErrorApp.UnknownError.left()
        }
    }


}