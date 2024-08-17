package com.example.walktracker.presentation.home_screen.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.BottomNavItem
import com.example.presentation.navigation.DashBoardBottomNavigation
import com.example.presentation.navigation.bottomBarItemList
import com.example.walktracker.presentation.Dimens
import com.example.walktracker.presentation.Dimens.SIXTY_DP
import com.example.walktracker.ui.theme.DarkGrayColor
import com.example.walktracker.ui.theme.GrayColor


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DashboardScreen() {
    val navHostController = rememberNavController()
    val shouldShowBottomBar = remember { mutableStateOf(true) }
    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar.value) {
                CustomBottomBar(navController = navHostController)
            }
        }
    ) {
        Modifier.padding(it)
        DashBoardBottomNavigation(navHostController,shouldShowBottomBar)
    }
}


@Composable
fun CustomBottomBar(navController: NavHostController) {
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    var currentDestination = navStackBackEntry?.destination
    Row(
        modifier = Modifier.run {
            padding(Dimens.paddingLarge)
                .clip(RoundedCornerShape(Dimens.paddingLarge))
                .background(
                    if (isSystemInDarkTheme()) DarkGrayColor
                    else GrayColor
                )
                .height(SIXTY_DP)
                .fillMaxWidth()
        },
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        bottomBarItemList.forEach { screen ->
            CustomNavItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.CustomNavItem(
    screen: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route?.substringAfterLast('.').toString() == screen.route.toString()
    } == true

    val background =
        if (selected) MaterialTheme.colorScheme.primary else Color.Transparent

    val contentColor =
        if (selected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .height(40.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route ?: "") {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(id = if (selected) screen.selectedIcon else screen.unselectedIcon),
                contentDescription = "icon",
                tint = contentColor
            )
            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.label ?: "",
                    color = contentColor,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}