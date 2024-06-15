package com.example.walktracker.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.walktracker.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetail(userDetail: UserDetail)

    @Update
    suspend fun updateUserDetail(userDetail: UserDetail)

    @Delete
    suspend fun deleteUserDetail(userDetail: UserDetail)

    @Query("SELECT * FROM user_table WHERE name = :name")
    fun getUserDetailByName(name: String): Flow<UserDetail>

    @Query("SELECT * FROM user_table")
    fun getAllUserDetails(): Flow<List<UserDetail>>

    @Query("UPDATE user_table SET name = :name WHERE name = :oldName")
    suspend fun updateUserName(oldName: String, name: String)

    @Query("UPDATE user_table SET heightInCm = :heightInCm WHERE name = :name")
    suspend fun updateUserHeight(name: String, heightInCm: String)

    @Query("UPDATE user_table SET weightInKg = :weightInKg WHERE name = :name")
    suspend fun updateUserWeight(name: String, weightInKg: String)

    @Query("UPDATE user_table SET gender = :gender WHERE name = :name")
    suspend fun updateUserGender(name: String, gender: Boolean)

    @Query("SELECT name FROM user_table WHERE name = :name")
    fun getUserName(name: String): Flow<String>?

    @Query("SELECT heightInCm FROM user_table WHERE name = :name")
    fun getUserHeight(name: String): Flow<String>?

    @Query("SELECT weightInKg FROM user_table WHERE name = :name")
    fun getUserWeight(name: String): Flow<String>?

    @Query("SELECT gender FROM user_table WHERE name = :name")
    suspend fun getUserGender(name: String): Boolean?
}