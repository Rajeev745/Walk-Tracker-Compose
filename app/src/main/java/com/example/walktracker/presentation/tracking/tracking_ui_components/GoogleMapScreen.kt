package com.example.walktracker.presentation.tracking.tracking_ui_components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import com.example.walktracker.R
import com.example.walktracker.presentation.tracking.service.PolyLines
import com.example.walktracker.utils.Utilities
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapScreen(
    isTrackingState: State<Boolean?>,
    pathPoints: LiveData<PolyLines>,
) {
    val pathPointsState by pathPoints.observeAsState(initial = emptyList())

    // Update tracking state and UI
    LaunchedEffect(isTrackingState) {
        // Equivalent to updateTracking method
        if (isTrackingState.value == false) {
            // Update button text and visibility
        }
    }

    val cameraPositionState = rememberCameraPositionState()

    // Update camera position to the latest location
    LaunchedEffect(pathPointsState) {
        if (pathPointsState.isNotEmpty() && pathPointsState.last().isNotEmpty()) {
            val lastLatLng = pathPointsState.last().last()
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(lastLatLng, 16f)
            )
        }
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
        pathPointsState.forEach { polyLine ->
            Polyline(points = polyLine, color = Color.Red, width = 8f)
        }
    }
}

@Composable
fun ToggleRunButton(isTracking: Boolean, onToggleRun: () -> Unit) {
    Button(onClick = onToggleRun) {
        Text(if (isTracking) "Stop" else "Start")
    }
}
