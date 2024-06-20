package com.example.walktracker.presentation.home_screen

import androidx.annotation.StringRes
import com.example.walktracker.R

sealed class HomeScreenBottomNav(val route: String, @StringRes val resourceId: Int) {
    object Home : HomeScreenBottomNav("home", R.string.home)
    object Statistics : HomeScreenBottomNav("search", R.string.statistics)
    object Profile : HomeScreenBottomNav("profile", R.string.profile)

}