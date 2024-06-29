package com.example.walktracker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String? = "",
    val heightInCm: String? = "",
    val weightInKg: String? = "",
    val age: String? = "",
    val gender: String? = ""
)