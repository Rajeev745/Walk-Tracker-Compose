package com.example.walktracker.presentation.input_details

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.walktracker.R
import com.example.walktracker.presentation.Dimens
import com.example.walktracker.presentation.Dimens.LargeCornerRadius
import com.example.walktracker.presentation.input_details.bottom_sheet.GenderBottomSheet
import com.example.walktracker.presentation.input_details.components.InputDetailCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDetailScreen() {

    val inputDetailViewModel: InputDetailViewModel = hiltViewModel()

    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(key1 = context) {
        inputDetailViewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Successful",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()  // Ensures the box fills the entire parent layout
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()  // Fills the width of the parent box
                .fillMaxHeight(0.3f) // Occupies 30% of the parent box height
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
                .border(  // Applies rounded corners and border
                    shape = RoundedCornerShape(
                        bottomStart = LargeCornerRadius,
                        bottomEnd = LargeCornerRadius
                    ),
                    border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary)
                ),
        ) {
            Text(
                text = "User Details",
                style = MaterialTheme.typography.headlineMedium,
                color = colorResource(
                    id = R.color.text_color
                ),
                modifier = Modifier.padding(Dimens.TopPaddingMedium)
            )
        }

        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.75f)
                .border(
                    shape = RoundedCornerShape(LargeCornerRadius),
                    border = BorderStroke(0.dp, color = MaterialTheme.colorScheme.background)
                ),
            shape = RoundedCornerShape(LargeCornerRadius),
        ) {
            InputDetailCard(inputDetailViewModel.state,
                inputDetailViewModel::onEvent,
                scope = scope,
                isBottomSheetVisible = isBottomSheetVisible,
                sheetState = sheetState,
                setBottomSheetVisible = { isVisible ->
                    isBottomSheetVisible = isVisible
                    scope.launch {
                        if (isVisible) sheetState.show() else sheetState.hide()
                    }
                }
            )
        }

        GenderBottomSheet(
            isBottomSheetVisible = isBottomSheetVisible,
            sheetState = sheetState,
            onDismiss = {
                scope.launch { sheetState.hide() }
                    .invokeOnCompletion { isBottomSheetVisible = !isBottomSheetVisible }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInputDetailScreen() {
    InputDetailScreen()
}