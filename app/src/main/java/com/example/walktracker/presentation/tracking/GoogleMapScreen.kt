package com.example.walktracker.presentation.tracking

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.walktracker.R
import com.example.walktracker.location.LocationService
import com.example.walktracker.utils.CONSTANTS.START_SERVICE
import com.example.walktracker.utils.Utilities
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapScreen() {

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(Unit) {
        val latLng = LatLng(13.0, 73.0)
        cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 17f)
    }

    val uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }

    val properties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.TERRAIN
            )
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
    ) {
        Marker(
            icon = Utilities.bitmapDescriptorFromVector(
                LocalContext.current, R.drawable.ic_fod_walking
            ),
            state = MarkerState(position = LatLng(13.0, 73.0))
        )
    }
}
