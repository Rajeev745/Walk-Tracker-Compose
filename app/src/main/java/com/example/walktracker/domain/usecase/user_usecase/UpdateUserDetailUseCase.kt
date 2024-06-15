package com.example.walktracker.domain.usecase.user_usecase

import com.example.walktracker.domain.model.UserDetail
import com.example.walktracker.domain.repository.UserDetailRepository

class UpdateUserDetailUseCase(private val userRepository: UserDetailRepository) {

    suspend operator fun invoke(userDetail: UserDetail) {
        userRepository.updateUserDetail(userDetail)
    }

}