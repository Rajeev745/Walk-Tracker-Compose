package com.example.walktracker.domain.usecase.location_usecase

data class SaveLocationUseCase(
    val insertLocationUseCase: InsertLocationUseCase,
    val getAllLocationsUseCase: GetAllLocationsUseCase,
)