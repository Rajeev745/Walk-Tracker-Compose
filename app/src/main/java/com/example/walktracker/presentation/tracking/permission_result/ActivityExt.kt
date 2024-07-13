package com.example.walktracker.presentation.tracking.permission_result

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings

/**
 * Extension function for Activity to open the app's settings screen.
 * This can be used to let the user manually change app permissions.
 */
fun Activity.openAppSettings() {
    // Create an intent to open the application's settings screen
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity) // Start the settings activity
}