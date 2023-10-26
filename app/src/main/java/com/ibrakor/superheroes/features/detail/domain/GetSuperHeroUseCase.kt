package com.ibrakor.superheroes.features.detail.domain

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.right
import com.ibrakor.superheroes.features.list.domain.BiographyRepository
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput
import com.ibrakor.superheroes.features.list.domain.SuperHeroRepository
import com.ibrakor.superheroes.features.list.domain.WorkRepository

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