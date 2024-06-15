package com.example.walktracker.domain.usecase.run_usecase

import com.example.walktracker.domain.model.Run
import com.example.walktracker.domain.repository.RunRepository

class DeleteRunUseCase(private val runRepository: RunRepository) {

    suspend operator fun invoke(run: Run) {
        runRepository.deleteRun(run)
    }

}