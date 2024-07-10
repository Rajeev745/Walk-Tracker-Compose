package com.example.walktracker.data.repository

import com.example.walktracker.data.local.LocationDao
import com.example.walktracker.domain.model.LocationEntity
import com.example.walktracker.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class LocationRepositoryImpl(private val locationDao: LocationDao): LocationRepository {

    override suspend fun insertLocation(locationEntity: LocationEntity) {
        locationDao.insert(locationEntity)
    }

    override fun getAllLocations(): Flow<List<LocationEntity>> {
        return locationDao.getAllLocations()
    }
}