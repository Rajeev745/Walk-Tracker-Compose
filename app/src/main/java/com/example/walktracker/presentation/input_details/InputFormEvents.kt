package com.example.walktracker.presentation.input_details

import com.example.walktracker.domain.model.UserDetail

sealed class InputFormEvents {
    data class UserNameChanged(val userName: String) : InputFormEvents()
    data class UserAgeChanged(val age: String) : InputFormEvents()
    data class UserHeightChanged(val height: String) : InputFormEvents()
    data class UserWeightChanged(val weight: String) : InputFormEvents()
    object Submit : InputFormEvents()
}

sealed class ValidationEvent {
    object Success : ValidationEvent()
}