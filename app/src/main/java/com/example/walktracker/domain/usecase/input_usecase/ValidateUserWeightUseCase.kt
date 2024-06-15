package com.example.walktracker.domain.usecase.input_usecase

class ValidateUserWeightUseCase {

    operator fun invoke(userWeight: String): InputValidationResult {
        val weight = userWeight.toDoubleOrNull()
        if (weight == 0.0) {
            return InputValidationResult(
                isValid = false,
                errorMessage = "Weight cannot be zero"
            )
        }

        if ((weight ?: 0.0) < 25) {
            return InputValidationResult(
                isValid = false,
                errorMessage = "Weight too low"
            )
        }

        return InputValidationResult(
            isValid = true
        )
    }
}