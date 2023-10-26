package com.ibrakor.superheroes.features.detail.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.ibrakor.avilaentapaspractica.app.serialization.GsonSerialization
import com.ibrakor.ejercicioformulario02.app.extensions.setUrl
import com.ibrakor.superheroes.R
import com.ibrakor.superheroes.databinding.ViewSuperHeroDetailBinding
import com.ibrakor.superheroes.features.list.data.BiographyDataRepository
import com.ibrakor.superheroes.features.list.data.SuperHeroesDataRepository
import com.ibrakor.superheroes.features.list.data.WorkDataRepository
import com.ibrakor.superheroes.features.list.data.local.BiographyLocalSource
import com.ibrakor.superheroes.features.list.data.local.SuperHeroesLocalSource
import com.ibrakor.superheroes.features.list.data.local.WorkLocalSource
import com.ibrakor.superheroes.features.list.data.remote.BiographyRemoteSource
import com.ibrakor.superheroes.features.list.data.remote.SuperHeroesRemoteSource
import com.ibrakor.superheroes.features.list.data.remote.WorkRemoteSource
import com.ibrakor.superheroes.features.list.domain.GetSuperHeroUseCase
import com.ibrakor.superheroes.features.list.domain.GetSuperHeroesFeedUseCase
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput
import com.ibrakor.superheroes.features.list.presentation.SuperHeroViewHolder
import com.ibrakor.superheroes.features.list.presentation.SuperHeroViewModel

class SuperHeroDetailActivity() : AppCompatActivity() {
    private val viewModel: SuperHeroViewModel by lazy {
        val superHeroRepository = SuperHeroesDataRepository(
            SuperHeroesRemoteSource(),
            SuperHeroesLocalSource(this, GsonSerialization())
        )
        val workRepository = WorkDataRepository(
            WorkRemoteSource(), WorkLocalSource(this,
                GsonSerialization()
            )
        )
        val biographyRepository = BiographyDataRepository(
            BiographyRemoteSource(),
            BiographyLocalSource(this, GsonSerialization())
        )
        SuperHeroViewModel(GetSuperHeroUseCase(superHeroRepository,workRepository,biographyRepository), GetSuperHeroesFeedUseCase(superHeroRepository, workRepository,biographyRepository))
    }
    private lateinit var binding: ViewSuperHeroDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()

        setupOberser()
        val heroId = intent.getIntExtra(SuperHeroViewHolder.SUPERHERO_ID_EXTRA, 1)

        viewModel.loadSuperHeroDetail(heroId)
    }

    private fun setupOberser() {
        val observer = Observer<SuperHeroViewModel.UiState>{
            it.superHero?.let {
                bindDataDetail(it)
            }
        }
        viewModel.uiState.observe(this,observer)
    }

    private fun bindDataDetail(it: SuperHeroOutput) {
        binding.apply {
            imageDetail.setUrl(it.superHero.imgUrl)
            superheroName.text=it.superHero.name
            superHeroFullName.text=it.biography.fullName
        }
    }

    private fun setupBinding() {
        binding= ViewSuperHeroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}