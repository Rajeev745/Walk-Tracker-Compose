package com.example.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.walktracker.R

val bottomBarItemList = listOf(
    BottomNavItem(
        label = "Home",
        selectedIcon = R.drawable.ic_home_selected,
        unselectedIcon = R.drawable.ic_home_unselected,
        route = DashBoardScreenRoute.HomeScreen,
        badgeCount = 0,
        hasNotification = false
    ),
    BottomNavItem(
        label = "Statistics",
        selectedIcon = R.drawable.ic_reports_selected,
        unselectedIcon = R.drawable.ic_reports_unselected,
        route = DashBoardScreenRoute.StatisticsScreen,
        badgeCount = 0,
        hasNotification = false
    ),
    BottomNavItem(
        label = "Profile",
        selectedIcon = R.drawable.ic_profile_selected,
        unselectedIcon = R.drawable.ic_profile_unselected,
        route = DashBoardScreenRoute.ProfileScreen,
        badgeCount = 0,
        hasNotification = false
    ),
)


data class BottomNavItem(
    val label: String?,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val route: DashBoardScreenRoute,
    val badgeCount: Int?,
    val hasNotification: Boolean?
)