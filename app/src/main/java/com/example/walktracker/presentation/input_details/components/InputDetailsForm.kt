package com.example.walktracker.presentation.input_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.walktracker.presentation.Dimens
import com.example.walktracker.presentation.input_details.InputFormEvents
import com.example.walktracker.presentation.input_details.InputFormState

@Composable
fun InputDetailsForm(
    state: InputFormState,
    event: (InputFormEvents) -> Unit,
    setBottomSheetVisible: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = Dimens.TopPaddingMedium)
    ) {
        InputField(
            label = "User Name",
            value = state.userName,
            onValueChange = { event(InputFormEvents.UserNameChanged(it)) },
            isError = state.userNameError != null
        )

        InputField(
            label = "Weight",
            value = state.weight,
            onValueChange = { event(InputFormEvents.UserWeightChanged(it)) },
            keyboardType = KeyboardType.Number,
            isError = state.weightError != null
        )

        InputField(
            label = "Height",
            value = state.height,
            onValueChange = { event(InputFormEvents.UserHeightChanged(it)) },
            keyboardType = KeyboardType.Number,
            isError = state.heightError != null
        )

        InputField(
            label = "Age",
            value = state.age,
            onValueChange = { event(InputFormEvents.UserAgeChanged(it)) },
            keyboardType = KeyboardType.Number,
            isError = state.ageError != null
        )

        InputField(label = "Gender",
            value = if (state.gender == true) "Male" else "Female",
            onValueChange = { event(InputFormEvents.UserAgeChanged(it)) },
            isReadOnly = true,
            isError = state.genderError != null,
            onClick = { setBottomSheetVisible(true) })
    }
}

@Preview
@Composable
fun InputDetailsFormPreview() {
    InputDetailsForm(state = InputFormState(), event = {}, setBottomSheetVisible = {})
}