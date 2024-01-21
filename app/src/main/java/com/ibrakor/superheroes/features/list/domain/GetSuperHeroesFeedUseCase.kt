package com.ibrakor.superheroes.features.list.domain

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.app.domain.left
import javax.inject.Inject

class GetSuperHeroesFeedUseCase @Inject constructor(private val superHeroRepository: SuperHeroRepository,
                                private val workRepository: WorkRepository,
                                private val biographyRepository: BiographyRepository
) {
    suspend operator fun invoke(): Either<ErrorApp, List<SuperHeroOutput>> {
        return superHeroRepository.obtainSuperHeros().map { superHeroes ->
            superHeroes.map { superHero ->
                val work = workRepository.obtainWork(superHero.id)
                val bio = biographyRepository.obtainBiography(superHero.id)
                if (work.isLeft() || bio.isLeft()) {
                    return ErrorApp.UnknownError.left()
                }
                SuperHeroOutput(superHero, work.get(), bio.get())
            }
        }
    }

}