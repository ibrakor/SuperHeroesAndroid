package com.ibrakor.superheroes.app.data.local

import android.content.Context
import com.ibrakor.avilaentapaspractica.app.serialization.JsonSerialization
import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.left
import com.ibrakor.ejercicioformulario02.app.right
import com.ibrakor.superheroes.features.list.domain.Work

class WorkLocalSource(private val context: Context, private val jsonSerialization: JsonSerialization) {
    val sharedPref = context.getSharedPreferences("works",Context.MODE_PRIVATE)

    fun getWork(heroId: String): Either<ErrorApp, Work>{
        return try {
            val jsonWork = sharedPref.getString(heroId,"")
            if (jsonWork.isNullOrEmpty()){
                ErrorApp.UnknownError.left()

            } else{
                jsonSerialization.fromJson(jsonWork, Work::class.java).right()

            }
        } catch (ex: Exception){
            ErrorApp.UnknownError.left()
        }
    }

    fun saveWork(heroId: String,work: Work): Either<ErrorApp, Boolean>{
        return try {


        with(sharedPref.edit()){
            val jsonWork = jsonSerialization.toJson(work, Work::class.java)
            putString(heroId,jsonWork)
            apply()
        }
            return true.right()
        } catch (ex: Exception){
            ErrorApp.UnknownError.left()
        }
    }
}