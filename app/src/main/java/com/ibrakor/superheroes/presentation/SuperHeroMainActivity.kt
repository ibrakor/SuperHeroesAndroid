package com.ibrakor.superheroes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.ibrakor.avilaentapaspractica.app.serialization.GsonSerialization
import com.ibrakor.superheroes.data.BiographyDataRepository
import com.ibrakor.superheroes.data.SuperHeroesDataRepository
import com.ibrakor.superheroes.data.WorkDataRepository
import com.ibrakor.superheroes.data.local.BiographyLocalSource
import com.ibrakor.superheroes.data.local.SuperHeroesLocalSource
import com.ibrakor.superheroes.data.local.WorkLocalSource
import com.ibrakor.superheroes.data.remote.BiographyRemoteSource
import com.ibrakor.superheroes.data.remote.SuperHeroesRemoteSource
import com.ibrakor.superheroes.data.remote.WorkRemoteSource
import com.ibrakor.superheroes.databinding.ActivityRecyclerSuperoHeroBinding
import com.ibrakor.superheroes.domain.GetSuperHeroUseCase
import com.ibrakor.superheroes.domain.GetSuperHeroesFeedUseCase
import com.ibrakor.superheroes.domain.SuperHeroOutput

class SuperHeroMainActivity : AppCompatActivity() {
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

    private val superHeroAdapter = SuperHeroAdapter()
    private lateinit var binding: ActivityRecyclerSuperoHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupView()
        setupObserver()
        viewModel.loadSuperHerosList()
    }

    private fun setupView() {
        binding.apply {
            recyclerSuperHero.layoutManager=LinearLayoutManager(
                this@SuperHeroMainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            recyclerSuperHero.adapter=superHeroAdapter
        }
    }

    private fun setupObserver() {
        val observer = Observer<SuperHeroViewModel.UiState>{

            if (it.isLoading){


            } else{


            }
            it.superHeroList?.let {
                bindDataSuperHero(it)
            }
        }
        viewModel.uiState.observe(this,observer)
    }



    private fun bindDataSuperHero(superHeroList: List<SuperHeroOutput>) {
        superHeroAdapter.submitList(superHeroList)
    }

    private fun setupBinding() {
        binding = ActivityRecyclerSuperoHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}