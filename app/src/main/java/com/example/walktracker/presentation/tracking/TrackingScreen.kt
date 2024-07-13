package com.example.walktracker.presentation.tracking

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.walktracker.presentation.tracking.permission_result.LocationPermissionTextProvider
import com.example.walktracker.presentation.tracking.permission_result.PermissionDialog
import com.example.walktracker.presentation.tracking.permission_result.openAppSettings

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun TrackingScreen() {

    val trackingViewModel: TrackingViewModel = hiltViewModel()
    val dialogQueue = trackingViewModel.visiblePermissionDialogQueue

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
            GoogleMapScreen()

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

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