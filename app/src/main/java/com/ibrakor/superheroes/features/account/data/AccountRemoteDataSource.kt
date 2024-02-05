package com.ibrakor.superheroes.features.account.data

import android.content.Context
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.ibrakor.superheroes.app.domain.Either
import com.ibrakor.superheroes.app.domain.ErrorApp
import com.ibrakor.superheroes.app.domain.left
import com.ibrakor.superheroes.app.domain.right
import com.ibrakor.superheroes.features.account.domain.AccountModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authentication: FirebaseAuth,
    private val authUI: AuthUI
) {
    suspend fun signOut(): Either<ErrorApp, Boolean> {
        return try {
            authUI.signOut(context).await()
            true.right()
        } catch (ex: Exception) {
            ErrorApp.ServerError.left()
        }
    }

    fun getAccount(): Either<ErrorApp, AccountModel?> {
        return try {
            authentication.currentUser?.toModel().right()
        } catch (ex: Exception) {
            ErrorApp.ServerError.left()
        }
    }
}