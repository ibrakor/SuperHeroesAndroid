package com.ibrakor.superheroes.features.account.data

import com.google.firebase.auth.FirebaseUser
import com.ibrakor.superheroes.features.account.domain.AccountModel

fun FirebaseUser.toModel() =
    AccountModel(this.uid, this.displayName, this.email, this.photoUrl.toString())