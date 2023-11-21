package com.ibrakor.superheroes.app.di

import com.ibrakor.avilaentapaspractica.app.serialization.GsonSerialization
import com.ibrakor.avilaentapaspractica.app.serialization.JsonSerialization
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun bindJsonSerialization(gsonSerialization: GsonSerialization): JsonSerialization
}