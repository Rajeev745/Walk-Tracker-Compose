package com.example.walktracker.presentation.tracking.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.example.walktracker.MainActivity
import com.example.walktracker.R
import com.example.walktracker.utils.CONSTANTS.ACTION_PAUSE_SERVICE
import com.example.walktracker.utils.CONSTANTS.ACTION_SHOW_TRACKING_SCREEN
import com.example.walktracker.utils.CONSTANTS.ACTION_START_OR_RESUME_SERVICE
import com.example.walktracker.utils.CONSTANTS.ACTION_STOP_SERVICE
import com.example.walktracker.utils.CONSTANTS.NOTIFICATION_CHANNEL_ID
import com.example.walktracker.utils.CONSTANTS.NOTIFICATION_CHANNEL_NAME
import com.example.walktracker.utils.CONSTANTS.NOTIFICATION_CONTENT_TITLE
import com.example.walktracker.utils.CONSTANTS.NOTIFICATION_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingService : LifecycleService() {

    var isFirstRun = true

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.let {
            when (it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    if (isFirstRun) {
                        startForegroundService()
                        isFirstRun = false
                    }
                    Log.d(TAG, "onStartCommand: ACTION_START_OR_RESUME_SERVICE")
                }

                ACTION_PAUSE_SERVICE -> {
                    Log.d(TAG, "onStartCommand: ACTION_PAUSE_SERVICE")
                }

                ACTION_STOP_SERVICE -> {
                    Log.d(TAG, "onStartCommand: ACTION_STOP_SERVICE")
                }

                else -> Unit
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun getPendingIntent(): PendingIntent? {
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE
        } else {
            FLAG_UPDATE_CURRENT
        }
        return PendingIntent.getActivity(
            this,
            PENDING_INTENT_REQUEST_CODE,
            Intent(this, MainActivity::class.java).also {
                it.action = ACTION_SHOW_TRACKING_SCREEN
            },
            flags
        )
    }

    private fun startForegroundService() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_run_notification)
            .setContentTitle(NOTIFICATION_CONTENT_TITLE)
            .setContentText("00.00.00")
            .setContentIntent(getPendingIntent())

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )

        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val TAG = "TrackingService"
        const val PENDING_INTENT_REQUEST_CODE = 0
    }
}