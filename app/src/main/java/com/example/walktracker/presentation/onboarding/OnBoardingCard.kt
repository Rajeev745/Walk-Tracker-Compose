package com.example.walktracker.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.walktracker.domain.model.onboarding.OnBoardingModel
import com.example.walktracker.presentation.Dimens

@Composable
fun OnBoardingCard(onBoardingModel: OnBoardingModel) {
    Column(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = onBoardingModel.image),
            contentDescription = onBoardingModel.title,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.LightGray)
                .border(
                    width = Dimens.ZeroDp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(Dimens.MediumCornerRadius)
                ),
            contentScale = ContentScale.Crop
        )

    }
}