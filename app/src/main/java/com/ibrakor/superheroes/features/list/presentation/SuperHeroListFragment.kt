package com.ibrakor.superheroes.features.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.ibrakor.avilaentapaspractica.app.serialization.GsonSerialization
import com.ibrakor.superheroes.R
import com.ibrakor.superheroes.databinding.FragmentSuperHeroesListBinding
import com.ibrakor.superheroes.features.MainActivity
import com.ibrakor.superheroes.features.list.data.BiographyDataRepository
import com.ibrakor.superheroes.features.list.data.SuperHeroesDataRepository
import com.ibrakor.superheroes.features.list.data.WorkDataRepository
import com.ibrakor.superheroes.features.list.data.local.BiographyLocalSource
import com.ibrakor.superheroes.features.list.data.local.SuperHeroesLocalSource
import com.ibrakor.superheroes.features.list.data.local.WorkLocalSource
import com.ibrakor.superheroes.features.list.data.remote.BiographyRemoteSource
import com.ibrakor.superheroes.features.list.data.remote.SuperHeroesRemoteSource
import com.ibrakor.superheroes.features.list.data.remote.WorkRemoteSource
import com.ibrakor.superheroes.features.list.domain.GetSuperHeroesFeedUseCase
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput

class SuperHeroListFragment : Fragment() {
    private var _binding: FragmentSuperHeroesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var skeleton: Skeleton

    private val superHeroAdapter = SuperHeroAdapter()

    private val viewModel: SuperHeroListViewModel by lazy {
        val superHeroRepository = SuperHeroesDataRepository(
            SuperHeroesRemoteSource(),
            SuperHeroesLocalSource((activity as MainActivity), GsonSerialization())
        )
        val workRepository = WorkDataRepository(
            WorkRemoteSource(), WorkLocalSource(
                (activity as MainActivity),
                GsonSerialization()
            )
        )
        val biographyRepository = BiographyDataRepository(
            BiographyRemoteSource(),
            BiographyLocalSource((activity as MainActivity), GsonSerialization())
        )
        SuperHeroListViewModel(
            GetSuperHeroesFeedUseCase(
                superHeroRepository,
                workRepository,
                biographyRepository
            )
        )
    }

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

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.loadSuperHerosList()
        skeleton=binding.recyclerSuperHero.applySkeleton(R.layout.view_super_hero_item,8)

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


