package com.example.walktracker.domain.usecase.user_usecase

import com.example.walktracker.domain.repository.UserDetailRepository

class GetUserHeightUseCase(private val userRepository: UserDetailRepository) {

    operator fun invoke(name: String) = userRepository.getUserHeight(name)
}