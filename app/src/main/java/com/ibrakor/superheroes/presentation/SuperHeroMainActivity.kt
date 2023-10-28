package com.ibrakor.superheroes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.ibrakor.avilaentapaspractica.app.serialization.GsonSerialization
import com.ibrakor.ejercicioformulario02.app.extensions.setUrl
import com.ibrakor.superheroes.data.BiographyDataRepository
import com.ibrakor.superheroes.data.SuperHeroesDataRepository
import com.ibrakor.superheroes.data.WorkDataRepository
import com.ibrakor.superheroes.data.local.BiographyLocalSource
import com.ibrakor.superheroes.data.local.SuperHeroesLocalSource
import com.ibrakor.superheroes.data.local.WorkLocalSource
import com.ibrakor.superheroes.data.remote.BiographyRemoteSource
import com.ibrakor.superheroes.data.remote.SuperHeroesRemoteSource
import com.ibrakor.superheroes.data.remote.WorkRemoteSource
import com.ibrakor.superheroes.databinding.ActivitySuperHeroMainBinding
import com.ibrakor.superheroes.domain.GetSuperHeroUseCase
import com.ibrakor.superheroes.domain.GetSuperHeroesFeedUseCase
import com.ibrakor.superheroes.domain.SuperHero
import com.ibrakor.superheroes.domain.SuperHeroOutput

class SuperHeroMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroMainBinding
    private val viewModel: SuperHeroViewModel by lazy {
        val superHeroRepository = SuperHeroesDataRepository(SuperHeroesRemoteSource(),
            SuperHeroesLocalSource(this,GsonSerialization())
        )
        val workRepository = WorkDataRepository(WorkRemoteSource(), WorkLocalSource(this,GsonSerialization()))
        val biographyRepository = BiographyDataRepository(BiographyRemoteSource(),
            BiographyLocalSource(this,GsonSerialization())
        )
        SuperHeroViewModel(GetSuperHeroUseCase(superHeroRepository,workRepository,biographyRepository), GetSuperHeroesFeedUseCase(superHeroRepository, workRepository,biographyRepository))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setupObserver()
        viewModel.loadSuperHero(620)
    }

    private fun setupObserver() {
        val observer = Observer<SuperHeroViewModel.UiState>{
            it.superHero?.let {
                bindDataSuperHero(it)
            }
        }
        viewModel.uiState.observe(this,observer)
    }

    private fun bindDataSuperHero(it: SuperHeroOutput) {
        binding.apply {
            superheroImage.setUrl(it.superHero.imgUrl)
            superheroFullname.text=it.biography.fullName
            superheroName.text=it.superHero.name
            superheroWork.text=it.work.occupation

        }
    }

    private fun bindView() {
        binding = ActivitySuperHeroMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}