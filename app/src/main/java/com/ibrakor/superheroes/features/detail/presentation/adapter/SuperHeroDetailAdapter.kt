package com.ibrakor.superheroes.features.detail.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ibrakor.superheroes.R

class SuperHeroDetailAdapter: ListAdapter<String, SuperHeroDetailViewHolder>(SuperHeroDetailDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_super_hero_images, parent,false)
        return SuperHeroDetailViewHolder(view)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: SuperHeroDetailViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}