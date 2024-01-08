package com.ibrakor.superheroes.app.data

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.app.domain.right
import com.ibrakor.superheroes.app.data.local.SuperHeroesLocalSource
import com.ibrakor.superheroes.app.data.remote.SuperHeroesRemoteSource
import com.ibrakor.superheroes.features.list.domain.SuperHero
import com.ibrakor.superheroes.features.list.domain.SuperHeroRepository
import javax.inject.Inject

class SuperHeroesDataRepository  @Inject constructor(
    private val remoteDataSource: SuperHeroesRemoteSource,
    private val localDataSource: SuperHeroesLocalSource
) : SuperHeroRepository {
    override suspend fun obtainSuperHero(heroId: Int): Either<ErrorApp, SuperHero> {
        val localResult = localDataSource.getSuperHeroById(heroId)
        localResult.mapLeft {
            remoteDataSource.getSuperHeroes().map {
                it.forEach {
                    if (it.id==heroId){
                        return it.right()
                    }
                }
            }
        }
        return localResult
    }
    override suspend fun obtainSuperHeros(): Either<ErrorApp, List<SuperHero>> {
        val localResult = localDataSource.getSuperHeroes()
        if (localResult.get().isEmpty()) {
            return remoteDataSource.getSuperHeroes().map {
                localDataSource.saveSuperHeroes(it)
                it
            }

        }
        return localResult
    }

}