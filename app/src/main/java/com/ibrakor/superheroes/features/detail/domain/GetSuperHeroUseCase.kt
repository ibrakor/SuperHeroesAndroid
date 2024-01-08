package com.ibrakor.superheroes.features.detail.domain

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.app.domain.right
import com.ibrakor.superheroes.features.list.domain.BiographyRepository
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput
import com.ibrakor.superheroes.features.list.domain.SuperHeroRepository
import com.ibrakor.superheroes.features.list.domain.WorkRepository
import javax.inject.Inject

class GetSuperHeroUseCase @Inject constructor(private val superHeroRepository: SuperHeroRepository,
                          private val workRepository: WorkRepository,
                          private val biographyRepository: BiographyRepository
) {
    suspend operator fun invoke(heroId: Int): Either<ErrorApp, SuperHeroOutput> {
        val superHero = superHeroRepository.obtainSuperHero(heroId).get()
        val work = workRepository.obtainWork(heroId).get()
        val biography = biographyRepository.obtainBiography(heroId).get()
        return SuperHeroOutput(superHero,work,biography).right()
    }
}