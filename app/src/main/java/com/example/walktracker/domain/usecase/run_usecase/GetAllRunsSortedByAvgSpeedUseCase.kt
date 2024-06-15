package com.example.walktracker.domain.usecase.run_usecase

import com.example.walktracker.domain.model.Run
import com.example.walktracker.domain.repository.RunRepository
import kotlinx.coroutines.flow.Flow

class GetAllRunsSortedByAvgSpeedUseCase(private val runRepository: RunRepository) {

    operator fun invoke(): Flow<List<Run>> {
        return runRepository.getAllRunsSortedByAvgSpeed()
    }

}