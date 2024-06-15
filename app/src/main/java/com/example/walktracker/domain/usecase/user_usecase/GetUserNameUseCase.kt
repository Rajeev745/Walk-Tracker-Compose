package com.example.walktracker.domain.usecase.user_usecase

import com.example.walktracker.domain.repository.UserDetailRepository
import kotlinx.coroutines.flow.Flow

class GetUserNameUseCase(private val userRepository: UserDetailRepository) {

    operator fun invoke(name: String): Flow<String>? {
        return userRepository.getUserName(name)
    }

}