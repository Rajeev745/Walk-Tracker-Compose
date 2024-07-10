package com.example.walktracker.domain.usecase.location_usecase

import com.example.walktracker.domain.model.LocationEntity
import com.example.walktracker.domain.repository.LocationRepository

class InsertLocationUseCase(private val locationRepository: LocationRepository) {

    suspend operator fun invoke(locationEntity: LocationEntity) {
        locationRepository.insertLocation(locationEntity)
    }
}