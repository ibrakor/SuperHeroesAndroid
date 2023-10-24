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
    override suspend fun obtainWork(superHeroId: String): Either<ErrorApp, Work> {
       val localResult=localSource.getWork(superHeroId)
        localResult.mapLeft {
    return  remoteSource.getWork(superHeroId).map {
        localSource.saveWork(superHeroId,it)
        it
    }

        }
        return localResult
    }
}