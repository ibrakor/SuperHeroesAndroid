package com.ibrakor.superheroes.data.api

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.superheroes.domain.Biography
import com.ibrakor.superheroes.domain.BiographyRepository

class BiographyDataRepository : BiographyRepository {

    override suspend fun obtainBiography(superHeroId: String): Either<ErrorApp, Biography> {
        local.getBiography(superHeroId)

        remote.getBiography(superHeroId)
    }
}