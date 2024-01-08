package com.ibrakor.superheroes.features.list.domain

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp

interface BiographyRepository {
    suspend fun obtainBiography(superHeroId: Int): Either<ErrorApp, Biography>
}