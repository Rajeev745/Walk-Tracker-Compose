package com.example.walktracker.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.walktracker.R
import com.example.walktracker.domain.model.onboarding.OnBoardingModel
import com.example.walktracker.presentation.Dimens

@Composable
fun OnBoardingCard(modifier: Modifier = Modifier, onBoardingModel: OnBoardingModel) {
    Column(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = onBoardingModel.image),
            contentDescription = onBoardingModel.title,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(color = Color.LightGray),
            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier
                .height(Dimens.VerticalSpaceHeightMedium)
                .fillMaxWidth()
        )

        Text(
            text = onBoardingModel.title,
            modifier = Modifier.padding(Dimens.TextPaddingMedium),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_color)
        )
    }
}