package com.ibrakor.ejercicioformulario02.app

sealed class ErrorApp {
    object UnknownError: ErrorApp()
    object NetworkError: ErrorApp()
}