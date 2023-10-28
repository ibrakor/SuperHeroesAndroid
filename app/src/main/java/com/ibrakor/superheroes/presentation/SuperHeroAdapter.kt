package com.ibrakor.superheroes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibrakor.superheroes.R
import com.ibrakor.superheroes.domain.SuperHeroOutput

class SuperHeroAdapter: ListAdapter<SuperHeroOutput,SuperHeroViewHolder>(SuperHeroDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_super_hero_item, parent,false)
        return SuperHeroViewHolder(view)
    }
    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}