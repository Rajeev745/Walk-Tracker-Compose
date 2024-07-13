package com.example.walktracker.presentation.tracking.service

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LifecycleService
import com.example.walktracker.utils.CONSTANTS.ACTION_PAUSE_SERVICE
import com.example.walktracker.utils.CONSTANTS.ACTION_START_OR_RESUME_SERVICE
import com.example.walktracker.utils.CONSTANTS.ACTION_STOP_SERVICE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingService: LifecycleService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.let {
            when(it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
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

    companion object {
        const val TAG = "TrackingService"
    }
}