package com.example.walktracker.presentation.input_details.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.walktracker.presentation.Dimens.BottomPaddingLarge
import com.example.walktracker.presentation.Dimens.MediumCornerRadius
import com.example.walktracker.presentation.Dimens.SpaceHeightSmall
import com.example.walktracker.presentation.Dimens.StartPaddingSmall
import com.example.walktracker.presentation.Dimens.ZeroDp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderBottomSheet(
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
) {

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            shape = RoundedCornerShape(topStart = MediumCornerRadius, topEnd = MediumCornerRadius),
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() }) {

            var selectedOption by remember { mutableStateOf(GenderOptions.Male) }
            val options = GenderOptions.values()

            Column(
                modifier = Modifier.padding(
                    bottom = BottomPaddingLarge,
                    start = StartPaddingSmall,
                    end = StartPaddingSmall
                )
            ) {

                Text(
                    text = "Select Gender",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = StartPaddingSmall),
                    style = MaterialTheme.typography.headlineMedium,
                )
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(MediumCornerRadius)
                            )
                            .border(
                                width = ZeroDp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(MediumCornerRadius)
                            )
                    ) {
                        RadioButton(
                            selected = selectedOption == option,
                            onClick = { selectedOption = option },
                            modifier = Modifier.selectable(
                                selected = selectedOption == option,
                                onClick = { selectedOption = option }
                            ),
                        )
                        Text(
                            modifier = Modifier.padding(vertical = StartPaddingSmall),
                            text = option.name,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(SpaceHeightSmall)
                    )
                }
            }
        }
    }
}