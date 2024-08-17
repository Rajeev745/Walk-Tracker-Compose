package com.example.walktracker.presentation.tracking.service

import java.util.concurrent.TimeUnit

object TrackingUtility {

    /**
     * Formats a given time in milliseconds into a stopwatch time string.
     *
     * This method converts the provided time in milliseconds into a formatted string representation.
     * The output can include or exclude milliseconds based on the `includeMillis` parameter.
     *
     * @param ms The time in milliseconds to be formatted.
     * @param includeMillis A boolean flag indicating whether to include milliseconds in the formatted output.
     *
     * @return A string representing the formatted time in the format `HH:mm:ss` or `HH:mm:ss:SS`.
     *
     * Functionality:
     * - Converts the given milliseconds (`ms`) into hours, minutes, and seconds.
     * - If `includeMillis` is `false`, the formatted string will exclude milliseconds and return `HH:mm:ss`.
     * - If `includeMillis` is `true`, the formatted string will include milliseconds and return `HH:mm:ss:SS`.
     * - Handles leading zeros for single-digit hours, minutes, seconds, and milliseconds to ensure a consistent format.
     */
    fun getFormattedStopWatchTime(ms: Long, includeMillis: Boolean): String {
        var milliseconds = ms
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        milliseconds -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
        if (!includeMillis) {
            return "${if (hours < 10) "0" else ""}$hours:" +
                    "${if (minutes < 10) "0" else ""}$minutes:" +
                    "${if (seconds < 10) "0" else ""}$seconds"
        }
        milliseconds -= TimeUnit.SECONDS.toMillis(seconds)
        milliseconds /= 10
        return "${if (hours < 10) "0" else ""}$hours:" +
                "${if (minutes < 10) "0" else ""}$minutes:" +
                "${if (seconds < 10) "0" else ""}$seconds:" +
                "${if (milliseconds < 10) "0" else ""}$milliseconds"
    }
}