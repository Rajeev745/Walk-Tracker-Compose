package com.example.walktracker.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.walktracker.R
import com.example.walktracker.presentation.Dimens
import com.example.walktracker.presentation.Dimens.ZeroDp

@Composable
fun WalkPrimaryButton(onClick: () -> Unit, text: String) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(Dimens.LargeButtonHeight)
            .background(Color.Transparent),
        shape = RoundedCornerShape(Dimens.MediumCardCornerRadius),
        contentPadding = PaddingValues(ZeroDp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            colorResource(id = R.color.button_color_two),
                            colorResource(id = R.color.button_color_one),
                        )
                    )
                ),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = text, style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White
            )
        }
    }

}
