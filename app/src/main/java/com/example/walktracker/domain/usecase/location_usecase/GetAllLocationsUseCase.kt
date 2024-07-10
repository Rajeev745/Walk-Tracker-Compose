package com.example.walktracker.domain.usecase.location_usecase

import com.example.walktracker.domain.model.LocationEntity
import com.example.walktracker.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class GetAllLocationsUseCase(private val locationRepository: LocationRepository) {

    operator fun invoke(): Flow<List<LocationEntity>> {
        return locationRepository.getAllLocations()
    }
}