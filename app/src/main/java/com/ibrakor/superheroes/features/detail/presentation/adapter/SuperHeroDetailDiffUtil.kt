package com.ibrakor.superheroes.features.detail.presentation.adapter

import androidx.recyclerview.widget.DiffUtil

class SuperHeroDetailDiffUtil: DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        TODO("Not yet implemented")
    }
}