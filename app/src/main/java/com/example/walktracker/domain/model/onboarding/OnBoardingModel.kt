package com.example.walktracker.domain.model.onboarding

import androidx.annotation.DrawableRes
import com.example.walktracker.R

data class OnBoardingModel(
    var title: String, @DrawableRes var image: Int,
)

val onBoardingList = listOf(
    OnBoardingModel("Discover your new running passion", R.drawable.onboarding_one),
    OnBoardingModel(
        "Gain insights with detailed run statistics and analytics",
        R.drawable.onboarding_two
    ),
    OnBoardingModel(
        "Set personal goals and track your achievements to reach new milestones.",
        R.drawable.onboarding_three
    ),
)