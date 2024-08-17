package com.example.walktracker.presentation.home_screen.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun StatisticsScreen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)) {

        Text(text = "Statistics Screen", style = MaterialTheme.typography.headlineMedium)
    }
}