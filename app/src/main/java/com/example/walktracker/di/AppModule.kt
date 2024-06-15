package com.example.walktracker.di

import android.content.Context
import androidx.room.Room
import com.example.walktracker.data.local.RunDao
import com.example.walktracker.data.local.RunDatabase
import com.example.walktracker.data.local.UserDetailDao
import com.example.walktracker.data.repository.RunRepositoryImpl
import com.example.walktracker.data.repository.UserDetailRepositoryImpl
import com.example.walktracker.domain.repository.RunRepository
import com.example.walktracker.domain.repository.UserDetailRepository
import com.example.walktracker.domain.usecase.input_usecase.ValidateUserAgeUseCase
import com.example.walktracker.domain.usecase.input_usecase.ValidateUserHeightUseCase
import com.example.walktracker.domain.usecase.input_usecase.ValidateUserNameUseCase
import com.example.walktracker.domain.usecase.input_usecase.ValidateUserWeightUseCase
import com.example.walktracker.domain.usecase.input_usecase.ValidationInputUseCase
import com.example.walktracker.domain.usecase.run_usecase.DeleteRunUseCase
import com.example.walktracker.domain.usecase.run_usecase.GetAllRunsSortedByAvgSpeedUseCase
import com.example.walktracker.domain.usecase.run_usecase.GetAllRunsSortedByCaloriesBurnedUseCase
import com.example.walktracker.domain.usecase.run_usecase.GetAllRunsSortedByDateUseCase
import com.example.walktracker.domain.usecase.run_usecase.GetAllRunsSortedByDistanceUseCase
import com.example.walktracker.domain.usecase.run_usecase.GetTotalAvgSpeedUseCase
import com.example.walktracker.domain.usecase.run_usecase.GetTotalCaloriesBurnedUseCase
import com.example.walktracker.domain.usecase.run_usecase.GetTotalDistanceUseCase
import com.example.walktracker.domain.usecase.run_usecase.GetTotalTimeInMillisUseCase
import com.example.walktracker.domain.usecase.run_usecase.InsertRunUseCase
import com.example.walktracker.domain.usecase.run_usecase.RunUseCase
import com.example.walktracker.domain.usecase.user_usecase.DeleteUserDetailUseCase
import com.example.walktracker.domain.usecase.user_usecase.GetAllUserDetailsUseCase
import com.example.walktracker.domain.usecase.user_usecase.GetUserDetailByNameUseCase
import com.example.walktracker.domain.usecase.user_usecase.GetUserGenderUseCase
import com.example.walktracker.domain.usecase.user_usecase.GetUserHeightUseCase
import com.example.walktracker.domain.usecase.user_usecase.GetUserNameUseCase
import com.example.walktracker.domain.usecase.user_usecase.GetUserWeightUseCase
import com.example.walktracker.domain.usecase.user_usecase.InsertUserDetailUseCase
import com.example.walktracker.domain.usecase.user_usecase.UpdateUserDetailUseCase
import com.example.walktracker.domain.usecase.user_usecase.UpdateUserGenderUseCase
import com.example.walktracker.domain.usecase.user_usecase.UpdateUserHeightUseCase
import com.example.walktracker.domain.usecase.user_usecase.UpdateUserNameUseCase
import com.example.walktracker.domain.usecase.user_usecase.UpdateUserWeightUseCase
import com.example.walktracker.domain.usecase.user_usecase.UserUseCase
import com.example.walktracker.utils.CONSTANTS.RUNNING_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context,
    ) = Room.databaseBuilder(
        app, RunDatabase::class.java, RUNNING_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(runDatabase: RunDatabase) = runDatabase.getRunDao()

    @Singleton
    @Provides
    fun provideUserDetailDao(runDatabase: RunDatabase) = runDatabase.getUserDetailsDao()

    @Singleton
    @Provides
    fun provideRunRepository(runDao: RunDao) = RunRepositoryImpl(runDao)

    @Singleton
    @Provides
    fun provideUserRepository(userDetailsDao: UserDetailDao): UserDetailRepository {
        return UserDetailRepositoryImpl(userDetailsDao)
    }

    @Singleton
    @Provides
    fun provideRunUseCase(runRepository: RunRepository): RunUseCase {
        return RunUseCase(
            deleteRunUseCase = DeleteRunUseCase(runRepository),
            insertRunUseCase = InsertRunUseCase(runRepository),
            getAllRunsSortedByAvgSpeedUseCase = GetAllRunsSortedByAvgSpeedUseCase(runRepository),
            getAllRunsSortedByCaloriesBurnedUseCase = GetAllRunsSortedByCaloriesBurnedUseCase(
                runRepository
            ),
            getAllRunsSortedByDateUseCase = GetAllRunsSortedByDateUseCase(runRepository),
            getAllRunsSortedByDistanceUseCase = GetAllRunsSortedByDistanceUseCase(runRepository),
            getTotalAvgSpeedUseCase = GetTotalAvgSpeedUseCase(runRepository),
            getTotalCaloriesBurnedUseCase = GetTotalCaloriesBurnedUseCase(runRepository),
            getTotalDistanceUseCase = GetTotalDistanceUseCase(runRepository),
            getTotalTimeInMillisUseCase = GetTotalTimeInMillisUseCase(runRepository)
        )
    }

    @Singleton
    @Provides
    fun provideUserDetailUseCase(userDetailRepository: UserDetailRepository): UserUseCase {
        return UserUseCase(
            getAllUserDetailsUseCase = GetAllUserDetailsUseCase(userDetailRepository),
            getUserGenderUseCase = GetUserGenderUseCase(userDetailRepository),
            getUserWeightUseCase = GetUserWeightUseCase(userDetailRepository),
            getUserHeightUseCase = GetUserHeightUseCase(userDetailRepository),
            getUserDetailByNameUseCase = GetUserDetailByNameUseCase(userDetailRepository),
            deleteUserDetailUseCase = DeleteUserDetailUseCase(userDetailRepository),
            getUserNameUseCase = GetUserNameUseCase(userDetailRepository),
            insertUserDetailUseCase = InsertUserDetailUseCase(userDetailRepository),
            updateUserDetailUseCase = UpdateUserDetailUseCase(userDetailRepository),
            updateUserGenderUseCase = UpdateUserGenderUseCase(userDetailRepository),
            updateUserHeightUseCase = UpdateUserHeightUseCase(userDetailRepository),
            updateUserNameUseCase = UpdateUserNameUseCase(userDetailRepository),
            updateUserWeightUseCase = UpdateUserWeightUseCase(userDetailRepository)
        )
    }

    @Singleton
    @Provides
    fun provideUserNameUseCase(): ValidationInputUseCase {
        return ValidationInputUseCase(
            validateUserNameUseCase = ValidateUserNameUseCase(),
            validateUserAgeUseCase = ValidateUserAgeUseCase(),
            validateUserHeightUseCase = ValidateUserHeightUseCase(),
            validateUserWeightUseCase = ValidateUserWeightUseCase()
        )
    }
}