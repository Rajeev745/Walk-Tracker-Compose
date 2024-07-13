package com.example.walktracker.presentation.tracking.permission_result

class LocationPermissionTextProvider : PermissionTextProvider {

    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined location permission. " +
                    "You can go to the app settings to grant it."
        } else {
            "This app needs access to your location so that you can run "
        }
    }
}