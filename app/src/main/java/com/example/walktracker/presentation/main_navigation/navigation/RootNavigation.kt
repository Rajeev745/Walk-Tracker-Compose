package com.example.walktracker.presentation.main_navigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.core.routes.SubRoutes
import com.example.core.routes.TotalScreens
import com.example.walktracker.presentation.home_screen.dashboard.DashboardScreen
import com.example.walktracker.presentation.input_details.InputDetailScreen
import com.example.walktracker.presentation.input_details.components.KeyboardAware
import com.example.walktracker.presentation.onboarding.OnBoardingScreen

@Composable
fun RootNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SubRoutes.DashBoardGraph) {

        navigation<SubRoutes.OnBoardingGraph>(startDestination = TotalScreens.OnBoardingScreen) {
            composable<TotalScreens.OnBoardingScreen> {
                OnBoardingScreen {
                    navController.navigate(TotalScreens.AuthScreen)
                }
            }
        }

        navigation<SubRoutes.AuthGraph>(startDestination = TotalScreens.AuthScreen) {
            composable<TotalScreens.AuthScreen> {
                KeyboardAware {
                    InputDetailScreen(
                        navigation = {
                            navController.navigate(TotalScreens.HomeScreen)
                        },
                        navController
                    )
                }
            }
        }

        navigation<SubRoutes.DashBoardGraph>(startDestination = TotalScreens.HomeScreen) {
            composable<TotalScreens.HomeScreen> {
                DashboardScreen()
            }
        }
    }
}
