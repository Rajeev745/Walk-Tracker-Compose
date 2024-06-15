package com.example.walktracker.presentation.input_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.walktracker.R
import com.example.walktracker.presentation.common.WalkPrimaryButton
import com.example.walktracker.presentation.input_details.InputFormEvents
import com.example.walktracker.presentation.input_details.InputFormState
import com.example.walktracker.ui.theme.WalkTrackerTheme
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDetailCard(
    state: InputFormState,
    event: (InputFormEvents) -> Unit,
    scope: CoroutineScope,
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    setBottomSheetVisible: (Boolean) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = if (isSystemInDarkTheme()) Color.DarkGray else MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun InputDetailCardPreview() {
    WalkTrackerTheme {
        InputDetailCard(state = InputFormState(
            userName = "John Doe", age = "25", height = "180", weight = "75.0"
        ),
            event = { /* No-op */ },
            scope = rememberCoroutineScope(),
            isBottomSheetVisible = true,
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            setBottomSheetVisible = {})
    }
}
