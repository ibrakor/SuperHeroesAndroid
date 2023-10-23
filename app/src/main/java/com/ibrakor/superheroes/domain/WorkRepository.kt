package com.ibrakor.superheroes.domain

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp

interface WorkRepository {
    suspend fun obtainWork(): Either<ErrorApp, Work>

}