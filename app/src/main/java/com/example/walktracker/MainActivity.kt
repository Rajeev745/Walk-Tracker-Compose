package com.example.walktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.walktracker.presentation.home_screen.RunScreen
import com.example.walktracker.presentation.home_screen.home.HomeScreen
import com.example.walktracker.presentation.home_screen.profile.ProfileScreen
import com.example.walktracker.presentation.home_screen.statistics.StatisticsScreen
import com.example.walktracker.presentation.input_details.InputDetailScreen
import com.example.walktracker.presentation.location.LocationService
import com.example.walktracker.presentation.location.RequestLocationPermission
import com.example.walktracker.presentation.navigation.NavigationDestination
import com.example.walktracker.presentation.onboarding.OnBoardingScreen
import com.example.walktracker.presentation.tracking.TrackingScreen
import com.example.walktracker.ui.theme.WalkTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var locationService: LocationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {

        }

        locationService = LocationService(this)

        setContent {
            WalkTrackerTheme(dynamicColor = false) {
                val navController = rememberNavController()

                NavHost(navController, startDestination = NavigationDestination.RunTrackerScreen) {
                    composable<NavigationDestination.Onboarding> {
                        OnBoardingScreen(navController)
                    }
                    composable<NavigationDestination.InputDetailScreen> {
                        InputDetailScreen()
                    }
                    composable<NavigationDestination.RunTrackerScreen> {
                        RunScreen(navController)
                    }
                }

                // State variables to manage location information and permission result text
                var locationText by remember { mutableStateOf("No location obtained :(") }
                var showPermissionResultText by remember { mutableStateOf(false) }
                var permissionResultText by remember { mutableStateOf("Permission Granted...") }

                RequestLocationPermission(
                    onPermissionGranted = {
                        // Callback when permission is granted
                        showPermissionResultText = true

                        // Attempt to get the last known user location
                        locationService.getLastUserLocation(
                            onGetLastLocationSuccess = {
                                locationText =
                                    "Location using LAST-LOCATION: LATITUDE: ${it.first}, LONGITUDE: ${it.second}"
                            },
                            onGetLastLocationFailed = { exception: Exception ->
                                showPermissionResultText = true
                                locationText =
                                    exception.localizedMessage ?: "Error Getting Last Location"
                            },
                            onGetLastLocationIsNull = {
                                locationService.getCurrentLocation(
                                    onGetCurrentLocationSuccess = {
                                        locationText =
                                            "Location using CURRENT-LOCATION: LATITUDE: ${it.first}, LONGITUDE: ${it.second}"
                                    },
                                    onGetCurrentLocationFailed = {
                                        showPermissionResultText = true
                                        locationText =
                                            it.localizedMessage
                                                ?: "Error Getting Current Location"
                                    }
                                )
                            }
                        )
                    },
                    onPermissionDenied = {
                        // Callback when permission is denied
                        showPermissionResultText = true
                        permissionResultText = "Permission Denied :("
                    },
                    onPermissionsRevoked = {
                        // Callback when permission is revoked
                        showPermissionResultText = true
                        permissionResultText = "Permission Revoked :("
                    }
                )
            }
        }
    }
}