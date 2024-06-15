package com.example.walktracker.domain.repository

import com.example.walktracker.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {

    suspend fun insertUserDetail(userDetail: UserDetail)

    suspend fun updateUserDetail(userDetail: UserDetail)

    suspend fun deleteUserDetail(userDetail: UserDetail)

    fun getUserDetailByName(name: String): Flow<UserDetail>?

    fun getAllUserDetails(): Flow<List<UserDetail>>

    suspend fun updateUserName(oldName: String, name: String)

    suspend fun updateUserHeight(name: String, heightInCm: String)

    suspend fun updateUserWeight(name: String, weightInKg: String)

    suspend fun updateUserGender(name: String, gender: Boolean)

    fun getUserName(name: String): Flow<String>?

    fun getUserHeight(name: String): Flow<String>?

    fun getUserWeight(name: String): Flow<String>?

    suspend fun getUserGender(name: String): Boolean?
}
