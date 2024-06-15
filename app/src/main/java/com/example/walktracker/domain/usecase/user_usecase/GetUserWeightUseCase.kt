package com.example.walktracker.domain.usecase.user_usecase

import com.example.walktracker.domain.repository.UserDetailRepository
import kotlinx.coroutines.flow.Flow

class GetUserWeightUseCase(private val userRepository: UserDetailRepository) {

    operator fun invoke(name: String): Flow<String>? {
        return userRepository.getUserWeight(name)

    }
}