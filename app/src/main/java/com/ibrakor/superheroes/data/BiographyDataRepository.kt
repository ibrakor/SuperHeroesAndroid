package com.ibrakor.superheroes.data

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.superheroes.data.local.BiographyLocalSource
import com.ibrakor.superheroes.data.remote.BiographyRemoteSource
import com.ibrakor.superheroes.data.remote.SuperHeroesRemoteSource
import com.ibrakor.superheroes.domain.Biography
import com.ibrakor.superheroes.domain.BiographyRepository

class BiographyDataRepository(private val remoteSource: BiographyRemoteSource, private val localSource: BiographyLocalSource) : BiographyRepository {

    override suspend fun obtainBiography(superHeroId: Int): Either<ErrorApp, Biography> {

        val localResult = localSource.getBiography(superHeroId.toString())
        localResult.mapLeft {
            return remoteSource.getBiography(superHeroId.toString()).map {
                localSource.saveBiography(superHeroId.toString(),it)
                it
            }
        }
        return localResult

    }
}