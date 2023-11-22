package com.ibrakor.superheroes.app.di

import androidx.lifecycle.ViewModel
import com.ibrakor.superheroes.app.data.BiographyDataRepository
import com.ibrakor.superheroes.app.data.SuperHeroesDataRepository
import com.ibrakor.superheroes.app.data.WorkDataRepository
import com.ibrakor.superheroes.features.list.domain.BiographyRepository
import com.ibrakor.superheroes.features.list.domain.SuperHeroRepository
import com.ibrakor.superheroes.features.list.domain.WorkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SuperHeroModule {

    @Binds
    abstract fun bindBiographyDataRepository(biographyDataRepository: BiographyDataRepository): BiographyRepository

    @Binds
    abstract fun bindWorkDataRepository(workDataRepository: WorkDataRepository): WorkRepository

    @Binds
    abstract fun bindSuperHeroDataRepository(superHeroesDataRepository: SuperHeroesDataRepository): SuperHeroRepository



}
