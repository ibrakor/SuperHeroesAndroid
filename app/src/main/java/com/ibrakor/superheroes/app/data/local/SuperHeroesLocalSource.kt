package com.ibrakor.superheroes.app.data.local

import android.content.Context
import com.ibrakor.avilaentapaspractica.app.serialization.JsonSerialization
import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.app.domain.left
import com.ibrakor.superheroes.app.domain.right
import com.ibrakor.superheroes.features.list.domain.SuperHero
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SuperHeroesLocalSource @Inject constructor(@ApplicationContext private val context: Context, private val jsonSerialization: JsonSerialization) {
    val sharedPreferences = context.getSharedPreferences("superheroes", Context.MODE_PRIVATE)

    fun getSuperHeroes(): Either<ErrorApp, List<SuperHero>> {
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

    fun saveSuperHeroes(models: List<SuperHero>): Either<ErrorApp, Boolean> {
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