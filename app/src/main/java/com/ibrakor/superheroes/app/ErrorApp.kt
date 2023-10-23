package com.ibrakor.ejercicioformulario02.app

sealed class ErrorApp {
    object UnknownError: ErrorApp()
    object MaxUserLimitExceeded: ErrorApp()
    object NetworkError: ErrorApp()
}