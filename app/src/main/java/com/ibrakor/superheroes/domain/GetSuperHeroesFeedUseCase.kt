package com.ibrakor.superheroes.domain

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp

class GetSuperHeroesFeedUseCase(private val superHeroRepository: SuperHeroRepository,
    private val workRepository: WorkRepository,
    private val biographyRepository: BiographyRepository) {
    suspend operator fun invoke(): Either<ErrorApp, List<Output>>{
        val superHeroesList: MutableList<Output> = mutableListOf()
        superHeroRepository.obtainSuperHeros().map { superHeroes ->
            superHeroes.forEach { superHero ->
                val work = workRepository.obtainWork(superHero.id).get()
                val bio = biographyRepository.obtainBiography(superHero.id).get()
                superHeroesList.add(Output(superHero, biography = bio, work = work))
            }
        }
    }

    data class Output(val superHero: SuperHero, val work: Work, val biography: Biography)
}