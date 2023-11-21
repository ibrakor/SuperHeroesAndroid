package com.ibrakor.superheroes.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

import com.ibrakor.superheroes.R
import com.ibrakor.superheroes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun changeFragment(fragment: Fragment){
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}