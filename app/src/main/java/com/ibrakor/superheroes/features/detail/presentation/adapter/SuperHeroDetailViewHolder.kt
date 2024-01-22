package com.ibrakor.superheroes.features.detail.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ibrakor.superheroes.app.extensions.setUrl
import com.ibrakor.superheroes.databinding.ViewSuperHeroImagesBinding

class SuperHeroDetailViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    private lateinit var binding: ViewSuperHeroImagesBinding

    fun bind(imageUrl: String){
        binding = ViewSuperHeroImagesBinding.bind(view)
        binding.apply {
            superHeroImage.setUrl(imageUrl)
        }
    }
}