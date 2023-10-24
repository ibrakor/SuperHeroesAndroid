package com.ibrakor.superheroes.domain

import com.ibrakor.ejercicioformulario02.app.Either
import com.ibrakor.ejercicioformulario02.app.ErrorApp

class GetSuperHeroesFeedUseCase(private val repository: SuperHeroRepository) {
    suspend operator fun invoke(): Either<ErrorApp, List<SuperHero>>{
        return repository.obtainSuperHeros()
    }
}