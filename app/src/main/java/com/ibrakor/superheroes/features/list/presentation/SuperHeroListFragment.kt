package com.ibrakor.superheroes.features.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.ibrakor.avilaentapaspractica.app.serialization.GsonSerialization
import com.ibrakor.superheroes.R
import com.ibrakor.superheroes.databinding.FragmentSuperHeroesListBinding
import com.ibrakor.superheroes.features.MainActivity
import com.ibrakor.superheroes.app.data.BiographyDataRepository
import com.ibrakor.superheroes.app.data.SuperHeroesDataRepository
import com.ibrakor.superheroes.app.data.WorkDataRepository
import com.ibrakor.superheroes.app.data.local.BiographyLocalSource
import com.ibrakor.superheroes.app.data.local.SuperHeroesLocalSource
import com.ibrakor.superheroes.app.data.local.WorkLocalSource
import com.ibrakor.superheroes.app.data.remote.BiographyRemoteSource
import com.ibrakor.superheroes.app.data.remote.SuperHeroesRemoteSource
import com.ibrakor.superheroes.app.data.remote.WorkRemoteSource
import com.ibrakor.superheroes.features.list.domain.GetSuperHeroesFeedUseCase
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperHeroListFragment : Fragment() {
    private var _binding: FragmentSuperHeroesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var skeleton: Skeleton

    private val superHeroAdapter = SuperHeroAdapter()

    private val viewModel by viewModels<SuperHeroListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperHeroesListBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }



    private fun setupView() {
        binding.apply {
            layoutToolbar.toolbar.apply {
                title = getString(R.string.app_name)
            }
            recyclerSuperHero.apply {
                layoutManager = LinearLayoutManager(
                    this@SuperHeroListFragment.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = superHeroAdapter
                superHeroAdapter.setEvent  {
                    findNavController().navigate(
                        SuperHeroListFragmentDirections.actionFromFragmentListToFragmentDetail(it.toString())
                    )
                }
                adapter = superHeroAdapter


            }
            skeleton=recyclerSuperHero.applySkeleton(R.layout.view_super_hero_item,8)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.loadSuperHerosList()

    }
    private fun setupObserver() {
        val observer = Observer<SuperHeroListViewModel.UiState> {

            if (it.isLoading) {
               showLoading()

            } else {

                hideLoading()
            }
            it.superHeroList?.let {
                bindDataSuperHero(it)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
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

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

}


