package com.example.walktracker.domain.usecase.user_usecase

import com.example.walktracker.domain.repository.UserDetailRepository

class UpdateUserHeightUseCase(private val userRepository: UserDetailRepository) {

    suspend operator fun invoke(name: String, heightInCm: String) {
        userRepository.updateUserHeight(name, heightInCm)
    }

}