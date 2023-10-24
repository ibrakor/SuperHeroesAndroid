package com.ibrakor.superheroes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.ibrakor.avilaentapaspractica.app.serialization.GsonSerialization
import com.ibrakor.ejercicioformulario02.app.extensions.setUrl
import com.ibrakor.superheroes.R
import com.ibrakor.superheroes.data.SuperHeroesDataRepository
import com.ibrakor.superheroes.data.local.SuperHeroesLocalSource
import com.ibrakor.superheroes.data.remote.SuperHeroesRemoteSource
import com.ibrakor.superheroes.databinding.ActivitySuperHeroMainBinding
import com.ibrakor.superheroes.domain.GetSuperHeroUseCase
import com.ibrakor.superheroes.domain.SuperHero

class SuperHeroMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroMainBinding
    private val viewModel: SuperHeroViewModel by lazy {
        SuperHeroViewModel(GetSuperHeroUseCase(SuperHeroesDataRepository(SuperHeroesRemoteSource(),
            SuperHeroesLocalSource(this,GsonSerialization())
        )))
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

    private fun bindDataSuperHero(it: SuperHero) {
        binding.apply {
            superheroImage.setUrl(it.imgUrl)
            superheroFullname.text=it.biography.fullName
            superheroName.text=it.name
            superheroWork.text=it.work.occupation

        }
    }

    private fun bindView() {
        binding = ActivitySuperHeroMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}