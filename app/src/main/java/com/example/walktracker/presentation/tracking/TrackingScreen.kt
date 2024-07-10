package com.example.walktracker.presentation.tracking

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun TrackingScreen(
    navController: NavController,
    cameraPermissionResultLauncher: ManagedActivityResultLauncher<String, Boolean>,
) {

    Box(modifier = Modifier.fillMaxSize()) {

        cameraPermissionResultLauncher.launch(
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) {
            GoogleMapScreen()
        }
    }
}