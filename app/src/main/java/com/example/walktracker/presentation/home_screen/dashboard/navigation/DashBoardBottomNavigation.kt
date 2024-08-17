package com.example.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.walktracker.presentation.home_screen.home.HomeScreen
import com.example.walktracker.presentation.home_screen.profile.ProfileScreen
import com.example.walktracker.presentation.home_screen.statistics.StatisticsScreen
import com.example.walktracker.presentation.tracking.TrackingScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DashBoardBottomNavigation(
    navHostController: NavHostController,
    shouldShowBottomBar: MutableState<Boolean>,
) {

    NavHost(
        navController = navHostController,
        startDestination = BottomSubRoutes.BottomDashBoardGraph
    ) {
        navigation<BottomSubRoutes.BottomDashBoardGraph>(startDestination = DashBoardScreenRoute.HomeScreen) {
            composable<DashBoardScreenRoute.HomeScreen> {
                shouldShowBottomBar.value = true
                HomeScreen {
                    navHostController.navigate(DashBoardScreenRoute.TrackingScreen)
                }
            }
            composable<DashBoardScreenRoute.StatisticsScreen> {
                StatisticsScreen()
            }

            composable<DashBoardScreenRoute.ProfileScreen> {
                ProfileScreen()
            }
        }

        navigation<BottomSubRoutes.TrackingGraph>(startDestination = DashBoardScreenRoute.TrackingScreen) {
            composable<DashBoardScreenRoute.TrackingScreen> {
                shouldShowBottomBar.value = false
                TrackingScreen()
            }
        }
    }
}