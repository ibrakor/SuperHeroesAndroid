package com.ibrakor.superheroes.features.account.domain

import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(): Either<ErrorApp, AccountModel?> {
        return accountRepository.getAccount()
    }
}