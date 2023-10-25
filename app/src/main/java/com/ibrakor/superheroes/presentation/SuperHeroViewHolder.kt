package com.ibrakor.superheroes.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.faltenreich.skeletonlayout.Skeleton
import com.ibrakor.ejercicioformulario02.app.extensions.setUrl
import com.ibrakor.superheroes.databinding.ActivitySuperHeroMainBinding
import com.ibrakor.superheroes.domain.SuperHeroOutput

class SuperHeroViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    private lateinit var binding: ActivitySuperHeroMainBinding
    private lateinit var skeleton: Skeleton

    fun bind(model: SuperHeroOutput){
        binding= ActivitySuperHeroMainBinding.bind(view)

        binding.apply {
            superheroImage.setUrl(model.superHero.imgUrl)
            superheroFullname.text=model.biography.fullName
            superheroName.text=model.superHero.name
            superheroWork.text=model.work.occupation

        }
    }

}