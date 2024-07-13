package com.example.walktracker.presentation.tracking

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrackingViewModel @Inject constructor() : ViewModel() {

    val visiblePermissionDialogQueue = mutableStateListOf<String>()

    /**
     * Method for dismissing dialog
     */
    fun dismissDialog() {
        visiblePermissionDialogQueue.removeFirst()
    }


    /**
     * Function to handle the result of a permission request.
     *
     * @param permission The permission that was requested.
     * @param isGranted A boolean indicating whether the permission was granted or not.
     */
    fun permissionResult(
        permission: String,
        isGranted: Boolean,
    ) {
        // If the permission is not granted and it is not already in the visible permission dialog queue
        if (!isGranted && !visiblePermissionDialogQueue.contains(permission)) {
            // Add the permission to the visible permission dialog queue
            visiblePermissionDialogQueue.add(permission)
        }
    }
}