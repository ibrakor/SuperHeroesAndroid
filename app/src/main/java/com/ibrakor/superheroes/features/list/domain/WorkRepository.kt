package com.ibrakor.superheroes.features.list.domain

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp

interface WorkRepository {
    suspend fun obtainWork(superHeroId: Int): Either<ErrorApp, Work>

}