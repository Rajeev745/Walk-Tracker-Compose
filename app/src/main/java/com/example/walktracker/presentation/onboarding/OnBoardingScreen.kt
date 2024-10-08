package com.example.walktracker.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.compose.rememberNavController
import com.example.walktracker.R
import com.example.walktracker.domain.model.onboarding.onBoardingList
import com.example.walktracker.presentation.Dimens
import com.example.walktracker.presentation.Dimens.SpaceHeightExtraLarge
import com.example.walktracker.presentation.common.WalkButton
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(navigation: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        val pagerState = rememberPagerState(pageCount = {
            onBoardingList.size
        })

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Next")
                    3 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp)
        ) { page ->

            Column {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(SpaceHeightExtraLarge)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                        .clip(RoundedCornerShape(Dimens.MediumCardCornerRadius))
                        .graphicsLayer {
                            val pageOffset =
                                ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)

                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )

                            rotationY = lerp(
                                start = 0f,
                                stop = 40f,
                                fraction = pageOffset.coerceIn(-0.2f, 1f),
                            )
                        },
                ) {
                    OnBoardingCard(onBoardingModel = onBoardingList[page])
                }
            }
        }

        Column {

            Text(
                text = onBoardingList[pagerState.currentPage].title,
                modifier = Modifier.padding(Dimens.paddingMedium),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.text_color)
            )

            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(Dimens.paddingLarge),
                horizontalArrangement = Arrangement.End
            ) {
                val buttonScope = rememberCoroutineScope()
                if (buttonState.value[0].isNotBlank()) {
                    WalkButton(
                        onClick = {
                            buttonScope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        },
                        text = buttonState.value[0],
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .height(Dimens.MediumButtonHeight)
                    )
                }
                WalkButton(
                    onClick = {
                        buttonScope.launch {
                            if (pagerState.currentPage == 2) {
                                // TODO: Navigate to different screen.
                            } else {
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                            }
                        }
                    }, text = buttonState.value[1], modifier = Modifier.height(Dimens.MediumButtonHeight)
                )
            }
        }
    }
}