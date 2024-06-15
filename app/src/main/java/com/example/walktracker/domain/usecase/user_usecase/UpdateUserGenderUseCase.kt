package com.example.walktracker.domain.usecase.user_usecase

import com.example.walktracker.domain.repository.UserDetailRepository

class UpdateUserGenderUseCase(private val userRepository: UserDetailRepository) {

    suspend operator fun invoke(name: String, gender: Boolean) {
        userRepository.updateUserGender(name, gender)
    }

}