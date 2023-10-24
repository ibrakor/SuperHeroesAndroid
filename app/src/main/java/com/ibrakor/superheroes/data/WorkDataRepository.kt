package com.ibrakor.superheroes.data

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.superheroes.data.local.WorkLocalSource
import com.ibrakor.superheroes.data.remote.WorkRemoteSource
import com.ibrakor.superheroes.domain.Work
import com.ibrakor.superheroes.domain.WorkRepository

class WorkDataRepository(
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