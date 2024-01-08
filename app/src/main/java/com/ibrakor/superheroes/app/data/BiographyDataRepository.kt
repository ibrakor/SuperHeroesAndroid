package com.ibrakor.superheroes.app.data

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.app.data.local.BiographyLocalSource
import com.ibrakor.superheroes.app.data.remote.BiographyRemoteSource
import com.ibrakor.superheroes.features.list.domain.Biography
import com.ibrakor.superheroes.features.list.domain.BiographyRepository
import javax.inject.Inject

class BiographyDataRepository @Inject constructor(private val remoteSource: BiographyRemoteSource, private val localSource: BiographyLocalSource) :
    BiographyRepository {

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