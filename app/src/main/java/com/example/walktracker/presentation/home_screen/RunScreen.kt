package com.example.walktracker.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun RunScreen(
    navController: NavController,
) {

    val items = listOf(
        HomeScreenBottomNav.Home,
        HomeScreenBottomNav.Statistics,
        HomeScreenBottomNav.Profile
    )
//
//    Scaffold(
//        bottomBar = {
//            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentDestination = navBackStackEntry?.destination
//            items.forEach { item ->
//
//            }
//        }
//    ) {
//
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    )
}