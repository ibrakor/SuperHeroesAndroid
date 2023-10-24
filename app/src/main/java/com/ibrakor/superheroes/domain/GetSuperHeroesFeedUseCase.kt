package com.ibrakor.superheroes.domain

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.right

class GetSuperHeroesFeedUseCase(private val superHeroRepository: SuperHeroRepository,
    private val workRepository: WorkRepository,
    private val biographyRepository: BiographyRepository) {
    suspend operator fun invoke(): Either<ErrorApp, List<SuperHeroOutput>>{
        val superHeroesList: MutableList<SuperHeroOutput> = mutableListOf()
        superHeroRepository.obtainSuperHeros().map { superHeroes ->
            superHeroes.forEach { superHero ->
                val work = workRepository.obtainWork(superHero.id).get()
                val bio = biographyRepository.obtainBiography(superHero.id).get()
                superHeroesList.add(SuperHeroOutput(superHero, biography = bio, work = work))
            }
        }
        return superHeroesList.right()
    }

}