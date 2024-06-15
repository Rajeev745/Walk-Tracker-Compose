package com.example.walktracker.domain.usecase.input_usecase

class ValidateUserNameUseCase {

    operator fun invoke(userName: String): InputValidationResult {
        if (userName.isBlank()) {
            return InputValidationResult(
                isValid = false,
                errorMessage = "Please enter the username"
            )
        }

        val isValidUserName = userName.any { it.isLetter() }
        if (!isValidUserName) {
            return InputValidationResult(
                isValid = false,
                errorMessage = "Username should only contain letters"
            )
        }

        return InputValidationResult(
            isValid = true
        )
    }
}