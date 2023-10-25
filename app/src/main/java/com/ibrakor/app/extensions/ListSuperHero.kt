package com.ibrakor.app.extensions

import com.ibrakor.superheroes.data.api.SuperHeroApiModel
import com.ibrakor.superheroes.data.api.toModel
import com.ibrakor.superheroes.domain.SuperHero

fun List<SuperHeroApiModel>.toModel(): List<SuperHero> {
    return this.map { it.toModel() }
}
