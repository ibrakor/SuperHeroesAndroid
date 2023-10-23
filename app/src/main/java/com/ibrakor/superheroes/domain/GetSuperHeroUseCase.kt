package com.ibrakor.superheroes.domain

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp

class GetSuperHeroUseCase(private val repository: SuperHeroRepository) {
    suspend operator fun invoke(heroId: Int):Either<ErrorApp, SuperHero>{
        return repository.obtainSuperHero(heroId)
    }
}