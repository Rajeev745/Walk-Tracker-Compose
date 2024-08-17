package com.example.core.routes

import kotlinx.serialization.Serializable

sealed class TotalScreens {

    @Serializable
    data object OnBoardingScreen

    @Serializable
    data object AuthScreen

    @Serializable
    data object HomeScreen

    @Serializable
    data object TrackingScreen
}

sealed class SubRoutes {
    @Serializable
    data object OnBoardingGraph

    @Serializable
    data object AuthGraph

    @Serializable
    data object DashBoardGraph
}

class Routes {

    @Serializable
    data object AppStartNavigation

    @Serializable
    object OnBoardingScreen

    @Serializable
    object AuthScreen

    @Serializable
    object HomeScreen

    @Serializable
    object TrackingScreen
}