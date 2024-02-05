package com.ibrakor.superheroes.features.account.data

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.features.account.domain.AccountModel
import com.ibrakor.superheroes.features.account.domain.AccountRepository
import javax.inject.Inject

class AccountDataRepository @Inject constructor(private val firebaseAccount: AccountRemoteDataSource) :
    AccountRepository {
    override suspend fun singOut(): Either<ErrorApp, Boolean> {
        return firebaseAccount.signOut()
    }

    override suspend fun getAccount(): Either<ErrorApp, AccountModel?> {
        return firebaseAccount.getAccount()
    }
}