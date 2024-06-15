package com.example.walktracker.presentation.input_details

data class InputFormState(
    val userName: String = "",
    val userNameError: String? = null,
    val age: String = "",
    val ageError: String? = null,
    val weight: String = "",
    val weightError: String? = null,
    val height: String = "",
    val heightError: String? = null,
    val gender: Boolean? = null,
    val genderError: String? = null
)