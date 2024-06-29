package com.example.walktracker.presentation.input_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.walktracker.R
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
            isError = state.userNameError != null,
            placeholder = stringResource(id = R.string.username_input)
        )

        InputField(
            label = "Weight",
            value = state.weight,
            onValueChange = { event(InputFormEvents.UserWeightChanged(it)) },
            keyboardType = KeyboardType.Number,
            isError = state.weightError != null,
            placeholder = stringResource(id = R.string.weight_input)
        )

        InputField(
            label = "Height",
            value = state.height,
            onValueChange = { event(InputFormEvents.UserHeightChanged(it)) },
            keyboardType = KeyboardType.Number,
            isError = state.heightError != null,
            placeholder = stringResource(id = R.string.height_input)
        )

        InputField(
            label = "Age",
            value = state.age,
            onValueChange = { event(InputFormEvents.UserAgeChanged(it)) },
            keyboardType = KeyboardType.Number,
            isError = state.ageError != null,
            placeholder = stringResource(id = R.string.age_input)
        )

        GenderField(
            label = "Gender",
            value = state.gender,
            onClickListener = { setBottomSheetVisible(true) })
    }
}

@Preview
@Composable
fun InputDetailsFormPreview() {
    InputDetailsForm(state = InputFormState(), event = {}, setBottomSheetVisible = {})
}