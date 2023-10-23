package com.ibrakor.superheroes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibrakor.superheroes.R
import com.ibrakor.superheroes.databinding.ActivitySuperHeroMainBinding

class SuperHeroMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_hero_main)
        bindView()
    }

    private fun bindView() {
        binding = ActivitySuperHeroMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}