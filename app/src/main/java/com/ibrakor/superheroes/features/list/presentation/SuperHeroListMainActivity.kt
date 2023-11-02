package com.ibrakor.superheroes.features.list.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.ibrakor.avilaentapaspractica.app.serialization.GsonSerialization
import com.ibrakor.ejercicioformulario02.app.ErrorApp
import com.ibrakor.ejercicioformulario02.app.extensions.visible
import com.ibrakor.superheroes.R
import com.ibrakor.superheroes.features.list.data.BiographyDataRepository
import com.ibrakor.superheroes.features.list.data.SuperHeroesDataRepository
import com.ibrakor.superheroes.features.list.data.WorkDataRepository
import com.ibrakor.superheroes.features.list.data.local.BiographyLocalSource
import com.ibrakor.superheroes.features.list.data.local.SuperHeroesLocalSource
import com.ibrakor.superheroes.features.list.data.local.WorkLocalSource
import com.ibrakor.superheroes.features.list.data.remote.BiographyRemoteSource
import com.ibrakor.superheroes.features.list.data.remote.SuperHeroesRemoteSource
import com.ibrakor.superheroes.features.list.data.remote.WorkRemoteSource
import com.ibrakor.superheroes.databinding.ActivityRecyclerSuperoHeroBinding
import com.ibrakor.superheroes.databinding.ActivityRecyclerToolbarSuperoHeroBinding
import com.ibrakor.superheroes.features.detail.presentation.SuperHeroDetailActivity
import com.ibrakor.superheroes.features.list.domain.GetSuperHeroesFeedUseCase
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput

class SuperHeroListMainActivity : AppCompatActivity() {
    private val viewModel: SuperHeroListViewModel by lazy {
        val superHeroRepository = SuperHeroesDataRepository(
            SuperHeroesRemoteSource(),
            SuperHeroesLocalSource(this,GsonSerialization())
        )
        val workRepository = WorkDataRepository(WorkRemoteSource(), WorkLocalSource(this,GsonSerialization()))
        val biographyRepository = BiographyDataRepository(
            BiographyRemoteSource(),
            BiographyLocalSource(this,GsonSerialization())
        )
        SuperHeroListViewModel( GetSuperHeroesFeedUseCase(superHeroRepository, workRepository,biographyRepository))
    }

    private val superHeroAdapter = SuperHeroAdapter()
    private lateinit var skeleton: Skeleton

    private lateinit var binding: ActivityRecyclerToolbarSuperoHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupView()
        setupObserver()
        viewModel.loadSuperHerosList()
    }
    private fun setupBinding() {
        binding = ActivityRecyclerToolbarSuperoHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView() {
        binding.apply {
            recyclerSuperHero.layoutManager=LinearLayoutManager(
                this@SuperHeroListMainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            superHeroAdapter.setEvent {
                navigateToDetail(it)
            }
            recyclerSuperHero.adapter=superHeroAdapter
            skeleton = recyclerSuperHero.applySkeleton(R.layout.view_super_hero_item,8)

        }
    }

    private fun setupObserver() {
        val observer = Observer<SuperHeroListViewModel.UiState>{

            if (it.isLoading){
                showLoading()

            } else{

                hideLoading()
            }
            it.superHeroList?.let {
                bindDataSuperHero(it)
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

    private fun showLoading() {
        skeleton.showSkeleton()
    }

    private fun hideLoading() {
        skeleton.showOriginal()
    }


    private fun bindDataSuperHero(superHeroList: List<SuperHeroOutput>) {
        superHeroAdapter.submitList(superHeroList)
    }
    private fun navigateToDetail(heroId: Int){
        val intent = Intent(this, SuperHeroDetailActivity::class.java)
        intent.putExtra(SuperHeroViewHolder.SUPERHERO_ID_EXTRA, heroId)
        startActivity(intent)
    }


}