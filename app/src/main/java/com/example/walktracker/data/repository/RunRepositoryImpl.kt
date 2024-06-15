package com.example.walktracker.data.repository

import com.example.walktracker.data.local.RunDao
import com.example.walktracker.domain.model.Run
import com.example.walktracker.domain.repository.RunRepository
import kotlinx.coroutines.flow.Flow

class RunRepositoryImpl(
    private val runDao: RunDao,
) : RunRepository {

    override suspend fun insertRun(run: Run) {
        runDao.insertRun(run)
    }

    override suspend fun deleteRun(run: Run) {
        runDao.deleteRun(run)
    }

    override fun getAllRunsSortedByDate(): Flow<List<Run>> {
        return runDao.getAllRunsSortedByDate()
    }

    override fun getAllRunsSortedByTimeInMillis(): Flow<List<Run>> {
        return runDao.getAllRunsSortedByTimeInMillis()
    }

    override fun getAllRunsSortedByCaloriesBurned(): Flow<List<Run>> {
        return runDao.getAllRunsSortedByCaloriesBurned()
    }

    override fun getAllRunsSortedByAvgSpeed(): Flow<List<Run>> {
        return runDao.getAllRunsSortedByAvgSpeed()
    }

    override fun getAllRunsSortedByDistance(): Flow<List<Run>> {
        return runDao.getAllRunsSortedByDistance()
    }

    override fun getTotalTimeInMillis(): Flow<Long> {
        return runDao.getTotalTimeInMillis()
    }

    override fun getTotalCaloriesBurned(): Flow<Int> {
        return runDao.getTotalCaloriesBurned()
    }

    override fun getTotalDistance(): Flow<Int> {
        return runDao.getTotalDistance()
    }

    override fun getTotalAvgSpeed(): Flow<Float> {
        return runDao.getTotalAvgSpeed()
    }
}