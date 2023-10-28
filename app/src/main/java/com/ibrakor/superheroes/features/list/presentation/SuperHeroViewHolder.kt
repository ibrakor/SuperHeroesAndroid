package com.ibrakor.superheroes.features.list.presentation

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.faltenreich.skeletonlayout.Skeleton
import com.ibrakor.ejercicioformulario02.app.extensions.setUrl
import com.ibrakor.superheroes.databinding.ViewSuperHeroItemBinding
import com.ibrakor.superheroes.features.detail.presentation.SuperHeroDetailActivity
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput

class SuperHeroViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewSuperHeroItemBinding
    companion object {
        const val SUPERHERO_ID_EXTRA = "superhero_id_extra"
    }



    fun bind(model: SuperHeroOutput, onClick: (Int) -> Unit) {
        binding = ViewSuperHeroItemBinding.bind(view)

        binding.apply {
            superheroImage.setUrl(model.superHero.images.lImage)
            superheroFullname.text = model.biography.fullName
            superheroName.text = model.superHero.name
            superheroWork.text = model.work.occupation
            submitArrow.setOnClickListener{
                onClick.invoke(model.superHero.id)

            }
        }
    }

}