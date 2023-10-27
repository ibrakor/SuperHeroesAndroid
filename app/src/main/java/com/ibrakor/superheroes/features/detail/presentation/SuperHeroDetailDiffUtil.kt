package com.ibrakor.superheroes.features.detail.presentation

import androidx.recyclerview.widget.DiffUtil
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput

class SuperHeroDetailDiffUtil: DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        TODO("Not yet implemented")
    }
}