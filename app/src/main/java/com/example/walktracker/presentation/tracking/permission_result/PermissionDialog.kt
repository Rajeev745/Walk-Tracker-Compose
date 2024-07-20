package com.example.walktracker.presentation.tracking.permission_result

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.walktracker.presentation.Dimens

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    isPermanentlyDeclined: Boolean,
    modifier: Modifier = Modifier,
) {

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Divider()
                Text(
                    text = if (isPermanentlyDeclined) {
                        "Grant Permission"
                    } else {
                        "Ok"
                    },
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isPermanentlyDeclined) {
                                onGoToAppSettingsClick()
                            } else {
                                onOkClick()
                            }
                        }
                        .padding(Dimens.paddingMedium)
                )
            }
        },
        title = {
            Text(
                text = "Permission Required",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        },
        text = {
            Text(
                text = permissionTextProvider.getDescription(isPermanentlyDeclined = isPermanentlyDeclined),
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }
    )
}