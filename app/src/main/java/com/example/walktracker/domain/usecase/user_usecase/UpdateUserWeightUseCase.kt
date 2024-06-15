package com.example.walktracker.domain.usecase.user_usecase

import com.example.walktracker.domain.repository.UserDetailRepository

class UpdateUserWeightUseCase(private val userRepository: UserDetailRepository) {

    suspend operator fun invoke(name: String, weightInKg: String) {
        userRepository.updateUserWeight(name, weightInKg)
    }

}