package com.ibrakor.superheroes.features.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrakor.superheroes.app.extensions.setUrl
import com.ibrakor.superheroes.app.extensions.setAlignmentColor
import com.ibrakor.superheroes.databinding.FragmentSuperHeroDetailBinding
import com.ibrakor.superheroes.features.list.domain.SuperHeroOutput
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperHeroDetailFragment : Fragment() {
    private val viewModel by viewModels<SuperHeroDetailViewModel>()

    private var _binding: FragmentSuperHeroDetailBinding? = null
    private val binding get() = _binding!!

    val args: SuperHeroDetailFragmentArgs by navArgs()

    private val superHeroDetailAdapter = SuperHeroDetailAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperHeroDetailBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {

        binding.apply {
            detailToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            detailsImagesRecyclerView.layoutManager = LinearLayoutManager(
                this@SuperHeroDetailFragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            detailsImagesRecyclerView.adapter=superHeroDetailAdapter

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.loadSuperHeroDetail(args.superHeroId.toInt())
    }

    private fun setupObservers() {
        val observer = Observer<SuperHeroDetailViewModel.UiState> {
            it.superHero?.let {
                bindDataDetail(it)
            }
            it.errorApp?.let {
                //showError(it)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindDataDetail(it: SuperHeroOutput) {
        binding.apply {
            imageDetail.setUrl(it.superHero.images.lImage)
            superheroName.text = it.superHero.name
            superHeroFullName.text = it.biography.fullName
            superHeroConections.text = it.superHero.connections
            superheroAlignment.text = it.biography.alignmente.uppercase()
            superheroAlignment.setAlignmentColor()
            speedValor.text = it.superHero.powerStats.speed
            fighValor.text = it.superHero.powerStats.combat
            intelligenceValor.text = it.superHero.powerStats.intelligence
        }
        val images: MutableList<String> = mutableListOf()
        images.add(it.superHero.images.lImage)
        images.add(it.superHero.images.sImage)
        images.add(it.superHero.images.mImage)
        images.add(it.superHero.images.lImage)
        superHeroDetailAdapter.submitList(images)
    }

    companion object {
        fun newInstance(param1: String) =
            SuperHeroDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}