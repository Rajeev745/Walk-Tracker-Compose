package com.example.walktracker.domain.usecase.run_usecase

import com.example.walktracker.domain.repository.RunRepository
import kotlinx.coroutines.flow.Flow

class GetTotalDistanceUseCase(private val runRepository: RunRepository) {

    operator fun invoke(): Flow<Int> {
        return runRepository.getTotalDistance()
    }

}