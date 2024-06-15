package com.example.walktracker.domain.usecase.run_usecase

data class RunUseCase(
    val deleteRunUseCase: DeleteRunUseCase,
    val insertRunUseCase: InsertRunUseCase,
    val getAllRunsSortedByAvgSpeedUseCase: GetAllRunsSortedByAvgSpeedUseCase,
    val getAllRunsSortedByCaloriesBurnedUseCase: GetAllRunsSortedByCaloriesBurnedUseCase,
    val getAllRunsSortedByDateUseCase: GetAllRunsSortedByDateUseCase,
    val getAllRunsSortedByDistanceUseCase: GetAllRunsSortedByDistanceUseCase,
    val getTotalAvgSpeedUseCase: GetTotalAvgSpeedUseCase,
    val getTotalCaloriesBurnedUseCase: GetTotalCaloriesBurnedUseCase,
    val getTotalTimeInMillisUseCase: GetTotalTimeInMillisUseCase,
    val getTotalDistanceUseCase: GetTotalDistanceUseCase
)