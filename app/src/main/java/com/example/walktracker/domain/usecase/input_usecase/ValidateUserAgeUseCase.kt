package com.example.walktracker.domain.usecase.input_usecase

class ValidateUserAgeUseCase {

    operator fun invoke(userAge: String): InputValidationResult {
        val age = userAge.toIntOrNull()
        if (age == 0) {
            return InputValidationResult(
                isValid = false,
                errorMessage = "Age cannot be zero"
            )
        }

        if ((age ?: 0) < 12) {
            return InputValidationResult(
                isValid = false,
                errorMessage = "Your Age must be at least 12 to use this App"
            )
        }

        return InputValidationResult(
            isValid = true
        )
    }
}