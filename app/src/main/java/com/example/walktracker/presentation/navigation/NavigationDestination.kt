package com.example.walktracker.presentation.navigation

import kotlinx.serialization.Serializable

sealed class NavigationDestination {
    @Serializable
    data object Onboarding : NavigationDestination()
    @Serializable
    data object InputDetailScreen : NavigationDestination()
    @Serializable
    data object WalkTrackerScreen : NavigationDestination()
}