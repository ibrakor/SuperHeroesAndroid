package com.ibrakor.superheroes.features.list.domain

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp

interface SuperHeroRepository {
    suspend fun obtainSuperHero(heroId: Int): Either<ErrorApp, SuperHero>
    suspend fun obtainSuperHeros(): Either<ErrorApp, List<SuperHero>>
}