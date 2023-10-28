package com.ibrakor.superheroes.features.list.domain

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.left
import com.ibrakor.ejercicioformulario02.app.right

class GetSuperHeroesFeedUseCase(private val superHeroRepository: SuperHeroRepository,
                                private val workRepository: WorkRepository,
                                private val biographyRepository: BiographyRepository
) {
    suspend operator fun invoke(): Either<ErrorApp, List<SuperHeroOutput>>{
        return superHeroRepository.obtainSuperHeros().map { superHeroes ->
            superHeroes.map {superHero ->
                val work = workRepository.obtainWork(superHero.id).get()
                val bio = biographyRepository.obtainBiography(superHero.id).get()
                SuperHeroOutput(superHero, work, bio)
            }
        }
    }

}