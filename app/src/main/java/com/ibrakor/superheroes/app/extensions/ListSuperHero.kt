package com.ibrakor.superheroes.app.extensions

import com.ibrakor.superheroes.features.list.data.api.SuperHeroApiModel
import com.ibrakor.superheroes.features.list.data.api.toModel
import com.ibrakor.superheroes.features.list.domain.SuperHero

fun List<SuperHeroApiModel>.toModel(): List<SuperHero> {
    return this.map { it.toModel() }
}
