package com.example.walktracker.domain.usecase.input_usecase

data class ValidationInputUseCase(
    val validateUserAgeUseCase: ValidateUserAgeUseCase,
    val validateUserNameUseCase: ValidateUserNameUseCase,
    val validateUserWeightUseCase: ValidateUserWeightUseCase,
    val validateUserHeightUseCase: ValidateUserHeightUseCase,
    val validateGenderUseCase: ValidateGenderUseCase
)