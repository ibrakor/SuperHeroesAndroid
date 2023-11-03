package com.ibrakor.superheroes.features.detail.presentation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout.Alignment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrakor.avilaentapaspractica.app.serialization.GsonSerialization
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.extensions.setUrl
import com.ibrakor.ejercicioformulario02.app.extensions.visible
import com.ibrakor.superheroes.R
import com.ibrakor.superheroes.app.extensions.setAlignmentColor
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
import com.ibrakor.superheroes.features.detail.domain.GetSuperHeroUseCase
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput
import com.ibrakor.superheroes.features.list.presentation.SuperHeroViewHolder

class SuperHeroDetailActivity() : AppCompatActivity() {
    private val viewModel: SuperHeroDetailViewModel by lazy {
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
        SuperHeroDetailViewModel(GetSuperHeroUseCase(superHeroRepository,workRepository,biographyRepository))
    }

    private lateinit var binding: ViewSuperHeroDetailBinding
    private val superHeroDetailAdapter = SuperHeroDetailAdapter()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupView()
        setupOberser()
        val heroId = intent.getIntExtra(SuperHeroViewHolder.SUPERHERO_ID_EXTRA, 1)

        viewModel.loadSuperHeroDetail(heroId)
    }

    private fun setupView() {
        binding.apply {
            detailsImagesRecyclerView.layoutManager=LinearLayoutManager(
                this@SuperHeroDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            detailsImagesRecyclerView.adapter= superHeroDetailAdapter
        }
    }

    private fun setupOberser() {
        val observer = Observer<SuperHeroDetailViewModel.UiState>{
            it.superHero?.let {
                bindDataDetail(it)
            }
            it.errorApp?.let {
                showError(it)
            }
        }
       viewModel.uiState.observe(this,observer)
    }

    private fun showError(errorApp: ErrorApp) {
        binding.apply {
            viewError.layoutError.visible()
        }
        when (errorApp){
            ErrorApp.UnknownError -> binding.viewError.messageError.text=getString(R.string.label_unknown_error)
            ErrorApp.NetworkError -> showNetworkError()
        }
    }
    private fun showNetworkError(){
        binding.apply {
            viewError.messageError.text=getString(R.string.label_network_error)
            viewError.imageError.setImageResource(R.drawable.ic_no_wifi)
        }
    }

    private fun bindDataDetail(it: SuperHeroOutput) {
        binding.apply {
            imageDetail.setUrl(it.superHero.images.lImage)
            superheroName.text=it.superHero.name
            superHeroFullName.text=it.biography.fullName
            superHeroConections.text=it.superHero.connections
            superheroAlignment.text=it.biography.alignmente.uppercase()
            superheroAlignment.setAlignmentColor()
            speedValor.text=it.superHero.powerStats.speed
            fighValor.text=it.superHero.powerStats.combat
            intelligenceValor.text=it.superHero.powerStats.intelligence
            topAppBar.setNavigationOnClickListener {
                finish()
            }

        }
        val images: MutableList<String> = mutableListOf()
        images.add(it.superHero.images.lImage)
        images.add(it.superHero.images.sImage)
        images.add(it.superHero.images.mImage)
        images.add(it.superHero.images.lImage)
        superHeroDetailAdapter.submitList(images)
    }

    private fun setupBinding() {
        binding= ViewSuperHeroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}