package com.ibrakor.superheroes.features.core

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ibrakor.superheroes.R
import com.ibrakor.superheroes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
        setupObserver()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
    }


    private fun setupObserver() {
        val observer = Observer<MainViewModel.UiState> { uiState ->
            if (!uiState.isLoading) {
                if (uiState.error == null) {
                    if (uiState.account == null) {
                        navController.navigate(R.id.fragment_login)
                    }
                }
            }

        }
        viewModel.uiState.observe(this, observer)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAccount()
    }
}