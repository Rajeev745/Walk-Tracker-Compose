package com.example.walktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.walktracker.presentation.home_screen.RunScreen
import com.example.walktracker.presentation.input_details.InputDetailScreen
import com.example.walktracker.presentation.navigation.NavigationDestination
import com.example.walktracker.presentation.onboarding.OnBoardingScreen
import com.example.walktracker.ui.theme.WalkTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {

        }

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
                        RunScreen()
                    }
                }
            }
        }
    }
}