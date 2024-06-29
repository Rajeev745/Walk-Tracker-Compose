package com.example.walktracker.data.repository

import com.example.walktracker.data.local.UserDetailDao
import com.example.walktracker.domain.model.UserDetail
import com.example.walktracker.domain.repository.UserDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDetailRepositoryImpl @Inject constructor(
    private val userDetailDao: UserDetailDao,
) : UserDetailRepository {

    override suspend fun insertUserDetail(userDetail: UserDetail) {
        userDetailDao.insertUserDetail(userDetail)
    }

    override suspend fun updateUserDetail(userDetail: UserDetail) {
        userDetailDao.updateUserDetail(userDetail)
    }

    override suspend fun deleteUserDetail(userDetail: UserDetail) {
        userDetailDao.deleteUserDetail(userDetail)
    }

    override fun getUserDetailByName(name: String): Flow<UserDetail>? {
        return userDetailDao.getUserDetailByName(name)
    }

    override fun getAllUserDetails(): Flow<List<UserDetail>> {
        return userDetailDao.getAllUserDetails()
    }

    override suspend fun updateUserName(oldName: String, name: String) {
        userDetailDao.updateUserName(oldName, name)
    }

    override suspend fun updateUserHeight(name: String, heightInCm: String) {
        userDetailDao.updateUserHeight(name, heightInCm)
    }

    override suspend fun updateUserWeight(name: String, weightInKg: String) {
        userDetailDao.updateUserWeight(name, weightInKg)
    }

    override suspend fun updateUserGender(name: String, gender: Boolean) {
        userDetailDao.updateUserGender(name, gender)
    }

    override fun getUserName(name: String): Flow<String>? {
        return userDetailDao.getUserName(name)
    }

    override fun getUserHeight(name: String): Flow<String>? {
        return userDetailDao.getUserHeight(name)
    }

    override fun getUserWeight(name: String): Flow<String>? {
        return userDetailDao.getUserWeight(name)
    }

    override suspend fun getUserGender(name: String): String? {
        return userDetailDao.getUserGender(name)
    }
}