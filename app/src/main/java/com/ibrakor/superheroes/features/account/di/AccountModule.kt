package com.ibrakor.superheroes.features.account.di

import com.ibrakor.superheroes.features.account.data.AccountDataRepository
import com.ibrakor.superheroes.features.account.domain.AccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AccountModule {

    @Binds
    abstract fun bindFirebase(firebase: AccountDataRepository): AccountRepository
}
