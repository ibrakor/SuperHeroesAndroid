package com.ibrakor.superheroes.domain

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp

interface BiographyRepository {
    suspend fun obtainBiography(superHeroId: String): Either<ErrorApp, Biography>
}