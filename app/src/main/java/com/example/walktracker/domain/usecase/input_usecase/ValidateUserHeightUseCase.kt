package com.example.walktracker.domain.usecase.input_usecase

class ValidateUserHeightUseCase {

    operator fun invoke(userHeight: String): InputValidationResult {
        val height = userHeight.toIntOrNull()
        if (height == 0) {
            return InputValidationResult(
                isValid = false,
                errorMessage = "Height cannot be zero"
            )
        }

        if ((height ?: 0) < 12) {
            return InputValidationResult(
                isValid = false,
                errorMessage = "Height too small"
            )
        }

        return InputValidationResult(
            isValid = true
        )
    }

}