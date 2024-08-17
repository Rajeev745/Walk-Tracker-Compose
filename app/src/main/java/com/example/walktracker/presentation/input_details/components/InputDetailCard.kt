package com.example.walktracker.presentation.input_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.walktracker.R
import com.example.walktracker.presentation.Dimens
import com.example.walktracker.presentation.common.WalkPrimaryButton
import com.example.walktracker.presentation.input_details.InputFormEvents
import com.example.walktracker.presentation.input_details.InputFormState
import com.example.walktracker.ui.theme.WalkTrackerTheme

@Composable
fun InputDetailCard(
    state: InputFormState,
    event: (InputFormEvents) -> Unit,
    setBottomSheetVisible: (Boolean) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Text(
            text = stringResource(id = R.string.user_details),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = Dimens.paddingLarge)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.75f)
                .align(Alignment.Center)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(Dimens.MediumCardCornerRadius)
                ),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(Dimens.MediumCardCornerRadius)
                    ),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                InputDetailsForm(
                    state = state, event = event, setBottomSheetVisible
                )

                BottomSection {
                    WalkPrimaryButton(
                        onClick = { event(InputFormEvents.Submit) },
                        text = stringResource(id = R.string.submit),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputDetailCardPreview() {
    WalkTrackerTheme {
        InputDetailCard(state = InputFormState(
            userName = "John Doe", age = "25", height = "180", weight = "75.0"
        ), event = { /* No-op */ }, setBottomSheetVisible = {})
    }
}
