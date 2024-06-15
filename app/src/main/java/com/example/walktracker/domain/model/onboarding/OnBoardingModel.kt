package com.example.walktracker.domain.model.onboarding

import androidx.annotation.DrawableRes
import com.example.walktracker.R

data class OnBoardingModel(
    var title: String, @DrawableRes var image: Int
)

val onBoardingList = listOf(
    OnBoardingModel("Discover your new running companion", R.drawable.onboarding_one),
    OnBoardingModel("Easily monitor your progress and achievements", R.drawable.onboarding_two),
    OnBoardingModel("Stay motivated with personalized running goals", R.drawable.onboarding_three),
    OnBoardingModel(
        "Gain insights with detailed run statistics and analytics", R.drawable.onboarding_four
    )
)