package com.example.walktracker.presentation.navigation

import kotlinx.serialization.Serializable

sealed class NavigationDestination {
    @Serializable
    data object Onboarding : NavigationDestination()
    @Serializable
    data object InputDetailScreen : NavigationDestination()
    @Serializable
    data object RunTrackerScreen : NavigationDestination()
    @Serializable
    data object HomeScreen : NavigationDestination()
    @Serializable
    data object ProfileScreen : NavigationDestination()
    @Serializable
    data object StatisticsScreen : NavigationDestination()
    @Serializable
    data object TrackingScreen: NavigationDestination()
}