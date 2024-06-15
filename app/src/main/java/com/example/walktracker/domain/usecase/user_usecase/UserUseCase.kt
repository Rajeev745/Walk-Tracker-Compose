package com.example.walktracker.domain.usecase.user_usecase

data class UserUseCase(
    val deleteUserDetailUseCase: DeleteUserDetailUseCase,
    val getAllUserDetailsUseCase: GetAllUserDetailsUseCase,
    val getUserDetailByNameUseCase: GetUserDetailByNameUseCase,
    val insertUserDetailUseCase: InsertUserDetailUseCase,
    val getUserGenderUseCase: GetUserGenderUseCase,
    val getUserHeightUseCase: GetUserHeightUseCase,
    val getUserNameUseCase: GetUserNameUseCase,
    val getUserWeightUseCase: GetUserWeightUseCase,
    val updateUserDetailUseCase: UpdateUserDetailUseCase,
    val updateUserGenderUseCase: UpdateUserGenderUseCase,
    val updateUserHeightUseCase: UpdateUserHeightUseCase,
    val updateUserNameUseCase: UpdateUserNameUseCase,
    val updateUserWeightUseCase: UpdateUserWeightUseCase,
)