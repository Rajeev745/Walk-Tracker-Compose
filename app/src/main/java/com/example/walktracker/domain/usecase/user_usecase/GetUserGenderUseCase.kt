package com.example.walktracker.domain.usecase.user_usecase

import com.example.walktracker.domain.repository.UserDetailRepository

class GetUserGenderUseCase(private val userRepository: UserDetailRepository) {

    suspend operator fun invoke(name: String) = userRepository.getUserGender(name)

}