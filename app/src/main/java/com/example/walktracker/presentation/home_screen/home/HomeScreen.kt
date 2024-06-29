package com.example.walktracker.presentation.home_screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.walktracker.presentation.Dimens.TopPaddingExtraLarge
import com.example.walktracker.presentation.common.StartTrackButton
import com.example.walktracker.presentation.navigation.NavigationDestination
import com.example.walktracker.presentation.tracking.TrackingScreen

@Composable
fun HomeScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(TopPaddingExtraLarge)
        )

        StartTrackButton(
            onClick = {
                 navController.navigate(NavigationDestination.TrackingScreen)
            },
            text = "START"
        )
    }
}