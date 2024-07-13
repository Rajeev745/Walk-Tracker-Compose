package com.example.walktracker.utils

object CONSTANTS {

    const val RUNNING_DATABASE = "running_table"

    const val LOCATION_PERMISSION_CODE = 100


    const val BACKGROUND_LOCATION_PERMISSION_CODE = 101

    const val LOCATION_UPDATE_INTERVAL = 3000L

    const val NOTIFICATION_CHANNEL_ID = "location"
    const val START_SERVICE = "service_start"
    const val STOP_SERVICE = "service_stop"

    // Tracking Service
    const val ACTION_START_OR_RESUME_SERVICE = "action_start_or_resume_service"
    const val ACTION_STOP_SERVICE = "action_stop_service"
    const val ACTION_PAUSE_SERVICE = "action_pause_service"
}