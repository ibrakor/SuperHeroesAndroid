package com.ibrakor.superheroes.app.di

import com.ibrakor.superheroes.app.data.api.SuperHeroApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
class SuperHeroProvides {

    @Provides
    fun provideApiService(retrofit: Retrofit) =
        retrofit.create(SuperHeroApiService::class.java)
}