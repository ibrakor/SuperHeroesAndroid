package com.ibrakor.superheroes.features.list.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput

class SuperHeroDiffUtil: DiffUtil.ItemCallback<SuperHeroOutput>() {
    override fun areItemsTheSame(oldItem: SuperHeroOutput, newItem: SuperHeroOutput): Boolean {
        return oldItem.superHero.id==newItem.superHero.id
    }

    override fun areContentsTheSame(oldItem: SuperHeroOutput, newItem: SuperHeroOutput): Boolean {
        return oldItem == newItem
    }
}