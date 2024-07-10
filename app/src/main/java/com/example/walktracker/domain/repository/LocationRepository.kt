package com.example.walktracker.domain.repository

import com.example.walktracker.domain.model.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun insertLocation(locationEntity: LocationEntity)

    fun getAllLocations(): Flow<List<LocationEntity>>
}