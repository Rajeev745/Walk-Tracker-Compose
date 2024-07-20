package com.example.walktracker.presentation.tracking

import TrackButton
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.walktracker.R
import com.example.walktracker.presentation.Dimens
import com.example.walktracker.presentation.tracking.permission_result.LocationPermissionTextProvider
import com.example.walktracker.presentation.tracking.permission_result.PermissionDialog
import com.example.walktracker.presentation.tracking.permission_result.openAppSettings
import com.example.walktracker.presentation.tracking.service.TrackingService
import com.example.walktracker.presentation.tracking.service.TrackingUtility
import com.example.walktracker.presentation.tracking.tracking_ui_components.GoogleMapScreen
import com.example.walktracker.utils.CONSTANTS.ACTION_PAUSE_SERVICE
import com.example.walktracker.utils.CONSTANTS.ACTION_START_OR_RESUME_SERVICE
import com.example.walktracker.utils.CONSTANTS.ACTION_STOP_SERVICE

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun TrackingScreen() {

    val trackingViewModel: TrackingViewModel = hiltViewModel()
    val dialogQueue = trackingViewModel.visiblePermissionDialogQueue

    val isTracking = TrackingService.isTracking.observeAsState()

    val currentTimeInMillis = TrackingService.timeRunInMillis.observeAsState()

    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.POST_NOTIFICATIONS
    )

    val context = LocalContext.current
    val activity = context as? Activity

    var shouldRequestPermission by remember { mutableStateOf(true) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissionResult ->
            locationPermissions.forEach { permission ->
                trackingViewModel.permissionResult(
                    permission = permission,
                    isGranted = permissionResult[permission] == true
                )
            }
        }
    )

    // Check permissions and request if necessary
    LaunchedEffect(Unit) {
        if (shouldRequestPermission) {
            val allPermissionsGranted = locationPermissions.all { permission ->
                ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }
            if (!allPermissionsGranted) {
                permissionLauncher.launch(locationPermissions)
            }
            shouldRequestPermission = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            GoogleMapScreen(
                isTrackingState = isTracking,
                pathPoints = TrackingService.pathPoints
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TrackButton(
                    onClick = {
                        if (isTracking.value == true) {
                            sendCommandToService(context, ACTION_PAUSE_SERVICE)
                        } else {
                            sendCommandToService(context, ACTION_START_OR_RESUME_SERVICE)
                        }
                    },
                    text = "Start",
                    iconResId = if (isTracking.value == true) R.drawable.ic_start_tracking else R.drawable.ic_pause_tracking,
                    contentDescription = "Start Walking",
                    Modifier
                        .height(70.dp)
                        .width(70.dp),
                )

                Text(
                    text = TrackingUtility.getFormattedStopWatchTime(currentTimeInMillis.value ?: 0L, true), style = TextStyle(
                        color = colorResource(id = R.color.text_color),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    ), modifier = Modifier

                        .background(
                            color = MaterialTheme.colorScheme.background, // Background color
                            shape = RoundedCornerShape(Dimens.extraLargeCornerRadius) // Corner radius
                        )
                        .padding(Dimens.paddingMedium)
                )

                TrackButton(
                    onClick = { sendCommandToService(context, ACTION_STOP_SERVICE) },
                    text = "Start",
                    iconResId = R.drawable.ic_stop_tracking,
                    contentDescription = "Start Walking",
                    Modifier
                        .height(70.dp)
                        .width(70.dp),
                )
            }
        }
    }

    dialogQueue.reversed().forEach { permission ->
        PermissionDialog(
            permissionTextProvider = when (permission) {
                Manifest.permission.ACCESS_FINE_LOCATION -> LocationPermissionTextProvider()
                else -> return@forEach
            },
            onDismiss = trackingViewModel::dismissDialog,
            onOkClick = {
                trackingViewModel.dismissDialog()
                permissionLauncher.launch(
                    arrayOf(permission)
                )
            },
            onGoToAppSettingsClick = { activity?.openAppSettings() },
            isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                activity ?: return@forEach,
                permission
            )
        )
    }
}

/**
 * Starts the TrackingService with a specified action.
 *
 * This method creates an intent to start the TrackingService and sets its action.
 * The service is then started with the specified context and action.
 *
 * @param context The context used to start the service.
 * @param action The action to be set on the intent before starting the service.
 */
private fun sendCommandToService(context: Context, action: String) {
    Intent(context, TrackingService::class.java).also {
        it.action = action
        context.startService(it)
    }
}