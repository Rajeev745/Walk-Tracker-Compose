package com.example.walktracker.presentation.location


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

class LocationService(private val context: Context) {
    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fun getLastUserLocation(
        onGetLastLocationSuccess: (Pair<Double, Double>) -> Unit,
        onGetLastLocationFailed: (Exception) -> Unit,
        onGetLastLocationIsNull: () -> Unit,
    ) {
        if (areLocationPermissionsGranted(context)) {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    onGetLastLocationSuccess(Pair(it.latitude, it.longitude))
                } ?: run {
                    onGetLastLocationIsNull()
                }
            }.addOnFailureListener {
                onGetLastLocationFailed(it)
            }
        }
    }

    fun getCurrentLocation(
        onGetCurrentLocationSuccess: (Pair<Double, Double>) -> Unit,
        onGetCurrentLocationFailed: (Exception) -> Unit,
        priority: Boolean = true,
    ) {
        val accuracy = if (priority) {
            Priority.PRIORITY_HIGH_ACCURACY
        } else {
            Priority.PRIORITY_BALANCED_POWER_ACCURACY
        }

        if (areLocationPermissionsGranted(context)) {
            fusedLocationProviderClient.getCurrentLocation(
                accuracy, CancellationTokenSource().token,
            ).addOnSuccessListener { location ->
                location?.let {
                    onGetCurrentLocationSuccess(Pair(it.latitude, it.longitude))
                }
            }.addOnFailureListener { exception ->
                onGetCurrentLocationFailed(exception)
            }
        }
    }

    private fun areLocationPermissionsGranted(context: Context): Boolean {
        return (ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
    }
}