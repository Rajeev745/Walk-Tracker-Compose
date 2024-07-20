package com.example.walktracker.utils

object CONSTANTS {

    // Database Table Name
    const val RUNNING_DATABASE = "running_table"

    const val LOCATION_PERMISSION_CODE = 100


    const val BACKGROUND_LOCATION_PERMISSION_CODE = 101

    const val LOCATION_UPDATE_INTERVAL = 5000L
    const val FASTEST_LOCATION_INTERVAL = 2000L
    const val TIMER_UPDATE_INTERVAL = 50L

    const val START_SERVICE = "service_start"
    const val STOP_SERVICE = "service_stop"

    // Tracking Service
    const val ACTION_START_OR_RESUME_SERVICE = "action_start_or_resume_service"
    const val ACTION_STOP_SERVICE = "action_stop_service"
    const val ACTION_PAUSE_SERVICE = "action_pause_service"
    const val ACTION_SHOW_TRACKING_SCREEN = "action_show_tracking_screen"

    // Notification For tracking
    const val NOTIFICATION_CHANNEL_ID = "tracking_notification_id"
    const val NOTIFICATION_CHANNEL_NAME = "tracking_notification_name"
    const val NOTIFICATION_ID = 1
    const val NOTIFICATION_CONTENT_TITLE = "Walk Tracker"
}