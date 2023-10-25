package com.ibrakor.superheroes.features.list.domain

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.right

class GetSuperHeroUseCase(private val superHeroRepository: SuperHeroRepository,
                          private val workRepository: WorkRepository,
                          private val biographyRepository: BiographyRepository
) {
    suspend operator fun invoke(heroId: Int):Either<ErrorApp, SuperHeroOutput>{
        val superHero = superHeroRepository.obtainSuperHero(heroId).get()
        val work = workRepository.obtainWork(heroId).get()
        val biography = biographyRepository.obtainBiography(heroId).get()
        return SuperHeroOutput(superHero,work,biography).right()
    }
}