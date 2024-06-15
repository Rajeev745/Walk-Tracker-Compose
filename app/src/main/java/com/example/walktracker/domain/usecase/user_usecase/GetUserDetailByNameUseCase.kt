package com.example.walktracker.domain.usecase.user_usecase

import com.example.walktracker.domain.repository.UserDetailRepository

class GetUserDetailByNameUseCase(private val userRepository: UserDetailRepository) {

    operator fun invoke(name: String) = userRepository.getUserDetailByName(name)

}