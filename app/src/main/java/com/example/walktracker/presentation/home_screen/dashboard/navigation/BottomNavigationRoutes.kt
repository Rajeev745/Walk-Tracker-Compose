package com.example.presentation.navigation

import kotlinx.serialization.Serializable

sealed class BottomSubRoutes {

    @Serializable
    data object BottomDashBoardGraph: BottomSubRoutes()

    @Serializable
    data object TrackingGraph: BottomSubRoutes()
}

sealed class DashBoardScreenRoute {

    @Serializable
    data object HomeScreen: DashBoardScreenRoute()

    @Serializable
    data object StatisticsScreen: DashBoardScreenRoute()

    @Serializable
    data object ProfileScreen: DashBoardScreenRoute()

    @Serializable
    data object TrackingScreen: DashBoardScreenRoute()

}