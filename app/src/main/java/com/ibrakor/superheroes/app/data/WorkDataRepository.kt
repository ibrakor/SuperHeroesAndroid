package com.ibrakor.superheroes.app.data

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.app.data.local.WorkLocalSource
import com.ibrakor.superheroes.app.data.remote.WorkRemoteSource
import com.ibrakor.superheroes.features.list.domain.Work
import com.ibrakor.superheroes.features.list.domain.WorkRepository
import javax.inject.Inject

class WorkDataRepository @Inject constructor(
    private val remoteSource: WorkRemoteSource,
    private val localSource: WorkLocalSource
): WorkRepository {
    override suspend fun obtainWork(superHeroId: Int): Either<ErrorApp, Work> {
       val localResult=localSource.getWork(superHeroId.toString())
        localResult.mapLeft {
    return  remoteSource.getWork(superHeroId.toString()).map {
        localSource.saveWork(superHeroId.toString(),it)
        it
    }

        }
        return localResult
    }
}