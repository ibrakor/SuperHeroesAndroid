package com.ibrakor.superheroes.features.list.domain

import com.ibrakor.superheroes.features.detail.domain.PowerStats

data class SuperHero(val id : Int, val name: String, val imgUrl: String, val connections: String, val powerStats: PowerStats)