package com.example.walktracker.domain.usecase.input_usecase

data class InputValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)