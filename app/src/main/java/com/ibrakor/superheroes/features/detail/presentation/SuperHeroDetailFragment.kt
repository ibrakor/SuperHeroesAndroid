package com.ibrakor.superheroes.features.detail.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ibrakor.avilaentapaspractica.app.serialization.GsonSerialization
import com.ibrakor.superheroes.databinding.FragmentSuperHeroDetailBinding
import com.ibrakor.superheroes.features.MainActivity
import com.ibrakor.superheroes.features.detail.domain.GetSuperHeroUseCase
import com.ibrakor.superheroes.features.list.data.BiographyDataRepository
import com.ibrakor.superheroes.features.list.data.SuperHeroesDataRepository
import com.ibrakor.superheroes.features.list.data.WorkDataRepository
import com.ibrakor.superheroes.features.list.data.local.BiographyLocalSource
import com.ibrakor.superheroes.features.list.data.local.SuperHeroesLocalSource
import com.ibrakor.superheroes.features.list.data.local.WorkLocalSource
import com.ibrakor.superheroes.features.list.data.remote.BiographyRemoteSource
import com.ibrakor.superheroes.features.list.data.remote.SuperHeroesRemoteSource
import com.ibrakor.superheroes.features.list.data.remote.WorkRemoteSource

class SuperHeroDetailFragment: Fragment() {
    private val viewModel: SuperHeroDetailViewModel by lazy {
        val superHeroRepository = SuperHeroesDataRepository(
            SuperHeroesRemoteSource(),
            SuperHeroesLocalSource((activity as MainActivity), GsonSerialization())
        )
        val workRepository = WorkDataRepository(
            WorkRemoteSource(), WorkLocalSource((activity as MainActivity),
                GsonSerialization()
            )
        )
        val biographyRepository = BiographyDataRepository(
            BiographyRemoteSource(),
            BiographyLocalSource((activity as MainActivity), GsonSerialization())
        )
        SuperHeroDetailViewModel(GetSuperHeroUseCase(superHeroRepository,workRepository,biographyRepository))
    }

    private var _binding: FragmentSuperHeroDetailBinding? = null
    private val binding get() = _binding!!

    val args = SuperHeroDetailFragmentArgs by navArgs()

}