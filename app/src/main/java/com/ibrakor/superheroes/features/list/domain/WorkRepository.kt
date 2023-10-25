package com.ibrakor.superheroes.features.list.domain

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp

interface WorkRepository {
    suspend fun obtainWork(superHeroId: Int): Either<ErrorApp, Work>

}