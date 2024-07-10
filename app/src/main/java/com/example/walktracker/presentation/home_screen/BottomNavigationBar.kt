package com.example.walktracker.presentation.home_screen

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.walktracker.presentation.Dimens.bottomNavTextSize
import com.example.walktracker.presentation.Dimens.iconSize
import com.example.walktracker.presentation.home_screen.home.HomeScreen
import com.example.walktracker.presentation.home_screen.profile.ProfileScreen
import com.example.walktracker.presentation.home_screen.statistics.StatisticsScreen
import com.example.walktracker.presentation.navigation.BottomNavigationItem
import com.example.walktracker.presentation.navigation.NavigationDestination
import com.example.walktracker.presentation.tracking.TrackingScreen

@Composable
fun BottomNavigationBar(cameraPermissionResultLauncher: ManagedActivityResultLauncher<String, Boolean>) {

    val navController = rememberNavController()

    var navigationSelectedItem by remember {
        mutableStateOf(0)
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NavigationBar {
            BottomNavigationItem().bottomNavigationItems()
                .forEachIndexed { index, bottomNavigationItem ->
                    NavigationBarItem(selected = index == navigationSelectedItem,
                        label = {
                            Text(text = bottomNavigationItem.label, fontSize = bottomNavTextSize)
                        },
                        icon = {
                            Icon(
                                painterResource(id = bottomNavigationItem.icon),
                                contentDescription = bottomNavigationItem.label,
                                modifier = Modifier.size(iconSize),
                                tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                            )
                        },
                        onClick = {
                            navigationSelectedItem = index
                            navController.navigate(bottomNavigationItem.navigationDestination) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                }
        }
    }) {
        NavHost(
            navController = navController,
            startDestination = NavigationDestination.HomeScreen,
            modifier = Modifier.padding(it)
        ) {
            composable<NavigationDestination.HomeScreen> {
                HomeScreen(navController)
            }
            composable<NavigationDestination.StatisticsScreen> {
                StatisticsScreen(navController)
            }
            composable<NavigationDestination.ProfileScreen> {
                ProfileScreen(navController)
            }
            composable<NavigationDestination.TrackingScreen> {
                TrackingScreen(navController, cameraPermissionResultLauncher) // Your tracking screen implementation
            }
        }
    }
}