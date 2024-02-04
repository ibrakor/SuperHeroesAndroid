package com.ibrakor.superheroes.features.account.domain

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp

interface AccountRepository {
    suspend fun singOut(): Either<ErrorApp, Boolean>
    suspend fun getAccount(): Either<ErrorApp, AccountModel?>
}