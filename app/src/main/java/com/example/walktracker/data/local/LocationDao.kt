package com.example.walktracker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.walktracker.domain.model.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Insert
    suspend fun insert(location: LocationEntity)

    @Query("SELECT * FROM locations")
    fun getAllLocations(): Flow<List<LocationEntity>>
}