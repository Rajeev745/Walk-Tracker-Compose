package com.example.walktracker.presentation.tracking.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.example.walktracker.MainActivity
import com.example.walktracker.R
import com.example.walktracker.utils.CONSTANTS.ACTION_PAUSE_SERVICE
import com.example.walktracker.utils.CONSTANTS.ACTION_SHOW_TRACKING_SCREEN
import com.example.walktracker.utils.CONSTANTS.ACTION_START_OR_RESUME_SERVICE
import com.example.walktracker.utils.CONSTANTS.ACTION_STOP_SERVICE
import com.example.walktracker.utils.CONSTANTS.FASTEST_LOCATION_INTERVAL
import com.example.walktracker.utils.CONSTANTS.LOCATION_UPDATE_INTERVAL
import com.example.walktracker.utils.CONSTANTS.NOTIFICATION_CHANNEL_ID
import com.example.walktracker.utils.CONSTANTS.NOTIFICATION_CHANNEL_NAME
import com.example.walktracker.utils.CONSTANTS.NOTIFICATION_CONTENT_TITLE
import com.example.walktracker.utils.CONSTANTS.NOTIFICATION_ID
import com.example.walktracker.utils.CONSTANTS.TIMER_UPDATE_INTERVAL
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

typealias PolyLine = MutableList<LatLng>
typealias PolyLines = MutableList<PolyLine>

/**
 * TrackingService is a foreground service responsible for tracking the user's location during a workout.
 * It leverages the FusedLocationProviderClient to receive location updates and manages the lifecycle of
 * these updates. The service can be started, resumed, paused, or stopped based on user actions, and it
 * handles the creation of a persistent notification to ensure the service isn't terminated by the system.
 *
 * The service observes a MutableLiveData object, `isTracking`, to update location tracking status.
 */
@AndroidEntryPoint
class TrackingService : LifecycleService() {

    // Flag to check if it's the first time the service is being run
    var isFirstRun = true

    // Client for receiving location updates
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // Time run in seconds
    private val timeRunInSeconds = MutableLiveData<Long>()

    private var isTimerEnabled = false
    private var lapTime = 0L
    private var timeRun = 0L
    private var timeStarted = 0L
    private var lastSecondTimestamp = 0L

    override fun onCreate() {
        super.onCreate()
        postInitialValues()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        isTracking.observe(this) {
            updateLocationTracking(it)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        addEmptyPolyLine()
        isTracking.postValue(true)

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
                    pauseService()
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

    /**
     * Starts the tracking timer for recording the elapsed time.
     *
     * This method initializes and starts a timer that updates the elapsed time in milliseconds and seconds.
     * It triggers the tracking process by setting the tracking state to `true` and records the start time.
     * The timer runs in a coroutine on the main thread, continuously updating the time until tracking is stopped.
     *
     * Functionality:
     * - Adds an empty polyline (for tracking purposes).
     * - Sets `isTracking` to `true` to indicate that tracking has started.
     * - Records the start time of the timer using `System.currentTimeMillis()`.
     * - Enables the timer by setting `isTimerEnabled` to `true`.
     * - Launches a coroutine that:
     *   - Continuously calculates and updates the elapsed time (`lapTime`) while tracking is active.
     *   - Updates the live data values `timeRunInMillis` and `timeRunInSeconds` for time elapsed in milliseconds and seconds, respectively.
     *   - Checks if one second has passed to update the `timeRunInSeconds`.
     *   - Delays each iteration by the specified `TIMER_UPDATE_INTERVAL` to control the update frequency.
     * - Accumulates the total run time in milliseconds when the timer stops.
     */
    private fun startTimer() {
        addEmptyPolyLine()
        isTracking.postValue(true)
        timeStarted = System.currentTimeMillis()
        isTimerEnabled = true
        CoroutineScope(Dispatchers.Main).launch {
            while (isTracking.value!!) {
                lapTime = System.currentTimeMillis() - timeStarted
                timeRunInMillis.postValue(timeRun + lapTime)

                if ((timeRunInMillis.value ?: 0) >= (lastSecondTimestamp + 1000L)) {
                    timeRunInSeconds.postValue((timeRunInSeconds.value ?: 0) + 1)
                    lastSecondTimestamp += 1000L
                }
                delay(TIMER_UPDATE_INTERVAL)
            }
            timeRun += lapTime
        }
    }

    /**
     * Adds the current location to the list of path points for tracking the user's route.
     *
     * @param location The current `Location` object representing the user's position.
     *
     * This method takes the provided `Location` object and converts it to a `LatLng` object
     * representing the latitude and longitude coordinates. It then adds this position to the
     * most recent path in the `pathPoints` list, which is used to track the user's route.
     *
     * The updated path points are posted to the `pathPoints` LiveData, which can be observed
     * by the UI or other components to update the displayed route in real-time.
     *
     * Note: The method is a no-op if the provided `location` is `null`.
     */
    private fun addPathPoints(location: Location?) {
        location?.apply {
            val pos = LatLng(location.latitude, location.longitude)
            pathPoints.value?.apply {
                last().add(pos)
                pathPoints.postValue(this)
            }
        }
    }

    /**
     * Initializes tracking-related values.
     * Sets the tracking status to false and clears any existing path points.
     */
    private fun postInitialValues() {
        isTracking.postValue(false)
        pathPoints.postValue(mutableListOf())
        timeRunInSeconds.postValue(0L)
        timeRunInMillis.postValue(0L)
    }

    /**
     * Adds an empty polyline (a list of locations) to the pathPoints LiveData,
     * used to represent the user's movement. If pathPoints is null,
     * it initializes it with an empty polyline.
     */
    private fun addEmptyPolyLine() = pathPoints.value?.apply {
        add(mutableListOf())
        pathPoints.postValue(this)
    } ?: pathPoints.postValue(mutableListOf(mutableListOf()))

    /**
     * Returns a PendingIntent that opens MainActivity, specifically showing the tracking screen.
     * Handles flag settings based on the Android version to ensure compatibility.
     */
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

    /**
     * Starts a foreground service with a persistent notification.
     * The notification shows tracking information and ensures the service isn't killed by the system.
     */
    private fun startForegroundService() {
        startTimer()
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

    /**
     * Method for pausing the service
     */
    private fun pauseService() {
        isTracking.postValue(false)
        isTimerEnabled = false
    }

    /**
     * Creates a notification channel for Android O and above.
     * This is necessary for displaying notifications in Android versions starting from Oreo.
     *
     * @param notificationManager The system service responsible for managing notifications.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )

        notificationManager.createNotificationChannel(channel)
    }

    /**
     * Updates the location tracking based on the provided tracking status.
     *
     * @param isTracking A boolean value indicating whether location tracking should be active.
     *
     * If `isTracking` is `true`, the method requests location updates using the `FusedLocationProviderClient`
     * with high accuracy. The location updates are provided at regular intervals specified by
     * `LOCATION_UPDATE_INTERVAL` and `FASTEST_LOCATION_INTERVAL`.
     *
     * If `isTracking` is `false`, the method removes location updates to stop tracking the location.
     *
     * Note: This method suppresses the lint warning for missing location permission (`@SuppressLint("MissingPermission")`).
     * Ensure that the necessary location permissions are checked and granted before calling this method.
     */
    @SuppressLint("MissingPermission")
    private fun updateLocationTracking(isTracking: Boolean) {
        if (isTracking) {
            val request = LocationRequest().apply {
                interval = LOCATION_UPDATE_INTERVAL
                fastestInterval = FASTEST_LOCATION_INTERVAL
                priority = PRIORITY_HIGH_ACCURACY
            }

            fusedLocationProviderClient.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )
        } else {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }
    }

    /**
     * A callback object for receiving location updates.
     *
     * This object extends `LocationCallback` and is used to handle location results when they are received.
     * The `onLocationResult` method is called whenever a new location result is available.
     *
     * If `isTracking` is `true`, the method iterates through the list of received locations and processes
     * each location by calling the `addPathPoints` method. The latitude and longitude of each location
     * are also logged for debugging purposes.
     */
    val locationCallback = object : LocationCallback() {

        override fun onLocationResult(result: LocationResult) {
            super.onLocationResult(result)
            if (isTracking.value!!) {
                result.locations.let { locations ->
                    for (location in locations) {
                        addPathPoints(location)
                        Log.d(TAG, "onLocationResult: ${location.latitude}, ${location.longitude}")
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "TrackingService"
        const val PENDING_INTENT_REQUEST_CODE = 0
        val isTracking = MutableLiveData<Boolean>()
        val pathPoints = MutableLiveData<PolyLines>()
        val timeRunInMillis = MutableLiveData<Long>()
    }
}