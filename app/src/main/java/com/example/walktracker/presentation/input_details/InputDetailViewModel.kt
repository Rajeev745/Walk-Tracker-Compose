package com.example.walktracker.presentation.input_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walktracker.domain.model.UserDetail
import com.example.walktracker.domain.usecase.input_usecase.ValidationInputUseCase
import com.example.walktracker.domain.usecase.user_usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputDetailViewModel @Inject constructor(
    private val validateInputUseCase: ValidationInputUseCase,
    private val userUseCase: UserUseCase,
) : ViewModel() {

    var state by mutableStateOf(InputFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: InputFormEvents) {
        when (event) {
            is InputFormEvents.UserNameChanged -> {
                state = state.copy(userName = event.userName)
            }

            is InputFormEvents.UserAgeChanged -> {
                state = state.copy(age = event.age)
            }

            is InputFormEvents.UserHeightChanged -> {
                state = state.copy(height = event.height)
            }

            is InputFormEvents.UserWeightChanged -> {
                state = state.copy(weight = event.weight)
            }

            is InputFormEvents.UserGenderChanged -> {
                state = state.copy(gender = event.gender)
            }

            is InputFormEvents.Submit -> {
                submitData()
            }

        }
    }

    private fun submitData() {
        val userNameResult = validateInputUseCase.validateUserNameUseCase(state.userName)
        val userAgeResult = validateInputUseCase.validateUserAgeUseCase(state.age)
        val userHeightResult = validateInputUseCase.validateUserHeightUseCase(state.height)
        val userWeightResult = validateInputUseCase.validateUserWeightUseCase(state.weight)
        val userGender = validateInputUseCase.validateGenderUseCase(state.gender)

        val hasError = listOf(
            userNameResult,
            userAgeResult,
            userHeightResult,
            userWeightResult,
            userGender
        ).any { !it.isValid }

        if (hasError) {
            state = state.copy(
                userNameError = userNameResult.errorMessage,
                ageError = userAgeResult.errorMessage,
                heightError = userHeightResult.errorMessage,
                weightError = userWeightResult.errorMessage,
                genderError = userGender.errorMessage
            )
            return
        }

        viewModelScope.launch {
            userUseCase.insertUserDetailUseCase(
                UserDetail(
                    name = state.userName,
                    age = state.age,
                    weightInKg = state.weight,
                    heightInCm = state.height,
                    gender = state.gender
                )
            )
        }
    }
}