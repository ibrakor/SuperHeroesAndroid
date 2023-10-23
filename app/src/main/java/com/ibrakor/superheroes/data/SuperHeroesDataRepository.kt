package com.ibrakor.superheroes.data

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.left
import com.ibrakor.ejercicioformulario02.app.right
import com.ibrakor.superheroes.data.local.SuperHeroesLocalSource
import com.ibrakor.superheroes.data.remote.SuperHeroesRemoteSource
import com.ibrakor.superheroes.domain.Biography
import com.ibrakor.superheroes.domain.BiographyRepository
import com.ibrakor.superheroes.domain.SuperHero
import com.ibrakor.superheroes.domain.SuperHeroRepository
import com.ibrakor.superheroes.domain.Work
import com.ibrakor.superheroes.domain.WorkRepository

class SuperHeroesDataRepository(private val remoteDataSource: SuperHeroesRemoteSource, private val localDataSource: SuperHeroesLocalSource): SuperHeroRepository,
    WorkRepository, BiographyRepository {
    override suspend fun obtainBiography(): Either<ErrorApp, Biography> {
        TODO("Not yet implemented")
    }

    override suspend fun obtainSuperHero(heroId: Int): Either<ErrorApp, SuperHero> {
        for (it in obtainSuperHeros().get()){
            if (it.id==heroId){
                return it.right()
            }
        }
        return ErrorApp.UnknownError.left()
    }

    override suspend fun obtainSuperHeros(): Either<ErrorApp, List<SuperHero>> {
        val localResult = localDataSource.getSuperHeroes()
        localResult.mapLeft {
            return remoteDataSource.getSuperHeroes().map {
                localDataSource.saveSuperHeroes(it)
                it
            }
        }
        return localResult
    }

    override suspend fun obtainWork(): Either<ErrorApp, Work> {
        TODO("Not yet implemented")
    }


}