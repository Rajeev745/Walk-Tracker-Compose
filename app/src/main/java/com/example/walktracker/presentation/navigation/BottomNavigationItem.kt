package com.example.walktracker.presentation.navigation

import com.example.walktracker.R

data class BottomNavigationItem(
    val label: String = "",
    val icon: Int = R.drawable.ic_run_home,
    val navigationDestination: NavigationDestination = NavigationDestination.HomeScreen
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = R.drawable.ic_run_home,
                navigationDestination = NavigationDestination.HomeScreen
            ),
            BottomNavigationItem(
                label = "Statistics",
                icon = R.drawable.ic_run_statistics,
                navigationDestination = NavigationDestination.StatisticsScreen
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = R.drawable.ic_run_profile,
                navigationDestination = NavigationDestination.ProfileScreen
            ),
        )
    }
}