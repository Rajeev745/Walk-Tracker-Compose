package com.example.walktracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.walktracker.presentation.main_navigation.navigation.RootNavigation
import com.example.walktracker.ui.theme.WalkTrackerTheme
import com.example.walktracker.utils.CONSTANTS.ACTION_SHOW_TRACKING_SCREEN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {

        }

        navigateToTrackingScreen(intent)

        enableEdgeToEdge()
        setContent {
            WalkTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        RootNavigation()
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        navigateToTrackingScreen(intent)
    }

    private fun navigateToTrackingScreen(intent: Intent) {
        if (intent.action == ACTION_SHOW_TRACKING_SCREEN) {
//            navigationDestination.value = NavigationDestination.TrackingScreen
        }
    }
}