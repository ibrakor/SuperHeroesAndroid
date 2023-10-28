package com.ibrakor.superheroes.domain

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp

interface SuperHeroRepository {
    suspend fun obtainSuperHero(heroId: Int): Either<ErrorApp, SuperHero>
    suspend fun obtainSuperHeros(): Either<ErrorApp, List<SuperHero>>

}