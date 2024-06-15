package com.example.walktracker.domain.usecase.user_usecase

import com.example.walktracker.domain.repository.UserDetailRepository

class UpdateUserNameUseCase(private val userRepository: UserDetailRepository) {

    suspend operator fun invoke(oldName: String, name: String) {
        userRepository.updateUserName(oldName, name)
    }

}