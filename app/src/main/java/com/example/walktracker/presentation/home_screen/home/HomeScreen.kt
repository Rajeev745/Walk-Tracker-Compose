package com.example.walktracker.presentation.home_screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.walktracker.presentation.Dimens
import com.example.walktracker.presentation.common.StartTrackButton

@Composable
fun HomeScreen(navigation: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.SpaceHeightExtraLarge)
        )

        StartTrackButton(
            onClick = { navigation() },
            text = "START"
        )
    }
}