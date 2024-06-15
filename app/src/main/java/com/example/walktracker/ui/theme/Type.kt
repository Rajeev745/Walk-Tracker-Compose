package com.example.walktracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.walktracker.R

val openSans = FontFamily(
    fonts = listOf(
        Font(R.font.open_sans_regular, FontWeight.Normal),
        Font(R.font.open_sans_bold, FontWeight.Bold),
        Font(R.font.open_sans_semibold, FontWeight.SemiBold),
    )
)

// Set of Material typography styles to start with
// body is "Text" in the Figma Design
// label small is xsmall in Figma Design
val Typography = Typography(
    displaySmall = TextStyle(
        fontSize = 24.sp,
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 36.sp,
    ),
    displayMedium = TextStyle(
        fontSize = 32.sp,
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 48.sp,
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    labelSmall = TextStyle(
        fontSize = 13.sp,
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 19.sp,
    ),
)