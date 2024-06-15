package com.example.walktracker.domain.usecase.user_usecase

import com.example.walktracker.domain.repository.UserDetailRepository

class GetAllUserDetailsUseCase(private val userRepository: UserDetailRepository) {

    operator fun invoke() = userRepository.getAllUserDetails()

}