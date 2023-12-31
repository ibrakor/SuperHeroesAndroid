package com.ibrakor.superheroes.features.list.data.local

import android.content.Context
import com.ibrakor.avilaentapaspractica.app.serialization.JsonSerialization
import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.left
import com.ibrakor.ejercicioformulario02.app.right
import com.ibrakor.superheroes.features.list.domain.SuperHero

class SuperHeroesLocalSource(private val context: Context, private val jsonSerialization: JsonSerialization) {
    val sharedPreferences = context.getSharedPreferences("superheroes", Context.MODE_PRIVATE)

    fun getSuperHeroes(): Either<ErrorApp, List<SuperHero>>{
        return try {
            val jsonHeroes = sharedPreferences.all as Map<String, String>
            val heroes = jsonHeroes.values.map {
                jsonSerialization.fromJson(it, SuperHero::class.java)
            }
            heroes.right()
        } catch (ex: Exception){
            ErrorApp.UnknownError.left()
        }
    }
    fun getSuperHeroById(id: Int): Either<ErrorApp, SuperHero> {
        return try {
            val jsonHeroes = sharedPreferences.all as Map<String, String>
            val heroJson = jsonHeroes.values.firstOrNull { json ->
                val superHero = jsonSerialization.fromJson(json, SuperHero::class.java)
                superHero.id == id
            }

            if (heroJson != null) {
                val hero = jsonSerialization.fromJson(heroJson, SuperHero::class.java)
                hero.right()
            } else {
                ErrorApp.UnknownError.left()
            }
        } catch (ex: Exception) {
            ErrorApp.UnknownError.left()
        }
    }

    fun saveSuperHeroes(models: List<SuperHero>): Either<ErrorApp, Boolean>{
        try {
            with(sharedPreferences.edit()){
                val jsonHeroes = models.map {
                    it.id.toString() to jsonSerialization.toJson(it, SuperHero::class.java)
                }.toMap()

                jsonHeroes.forEach{(id, jsonHero) ->
                    putString(id,jsonHero)
                }
                apply()
            }
            return true.right()
        }catch (ex: Exception){
            return ErrorApp.UnknownError.left()
        }
    }
}