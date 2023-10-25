package com.ibrakor.superheroes.features.list.domain

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp

interface BiographyRepository {
    suspend fun obtainBiography(superHeroId: Int): Either<ErrorApp, Biography>
}