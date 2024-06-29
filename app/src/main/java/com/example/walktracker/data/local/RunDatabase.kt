package com.example.walktracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.walktracker.domain.model.Run
import com.example.walktracker.domain.model.UserDetail

@Database(
    entities = [Run::class, UserDetail::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RunDatabase : RoomDatabase() {

    abstract fun getRunDao(): RunDao

    abstract fun getUserDetailsDao(): UserDetailDao
}