package com.ibrakor.superheroes.app.domain

sealed class ErrorApp {
    object UnknownError: ErrorApp()
    object NetworkError: ErrorApp()
}