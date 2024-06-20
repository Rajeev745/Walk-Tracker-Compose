package com.example.walktracker.presentation.input_details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.background
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
    ) {

        InputDetailCard(inputDetailViewModel.state,
            inputDetailViewModel::onEvent,
            setBottomSheetVisible = { isVisible ->
                isBottomSheetVisible = isVisible
                scope.launch {
                    if (isVisible) sheetState.show() else sheetState.hide()
                }
            }
        )

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