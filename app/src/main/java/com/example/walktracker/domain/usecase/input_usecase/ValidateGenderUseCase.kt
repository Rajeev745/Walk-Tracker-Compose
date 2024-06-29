package com.example.walktracker.domain.usecase.input_usecase

class ValidateGenderUseCase {

    operator fun invoke(gender: String): InputValidationResult {
        if (gender.isBlank()) {
            return InputValidationResult(
                isValid = false,
                errorMessage = "Please Select the Gender"
            )
        }

        return InputValidationResult(
            isValid = true
        )
    }
}