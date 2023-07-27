@file:OptIn(ExperimentalFoundationApi::class)

package com.example.instacloneapp3.presentation.ui.screens.home_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.instacloneapp3.presentation.mock_data.User
import com.example.instacloneapp3.presentation.ui.core.components.HorizontalDraggableScreen
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.ui.screens.home_screen.components.UserFeedsList
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel


/*
Home Screen - Contains Feeds from other users
 */
val user = User()

val storyImageModifier = Modifier
    .size(90.dp)
    .clip(CircleShape)

val colorStops = arrayOf(
    0.0f to Color(0xFFd71069),
    0.5f to Color(0xFFe25d6a),
    1f to Color(0xFFe9ad55)
)
@Composable
fun HomeScreen(
    rootNavController: NavHostController,
    navigationViewModel: NavigationViewModel,
    pagerState: PagerState,
    screenStackIndex: Int,
    ) {


    HorizontalDraggableScreen(
        dragEnabled = false,
        screenStackIndex = screenStackIndex,
        navigationViewModel = navigationViewModel,
     ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            //--Lazy Column for Feeds--
            UserFeedsList(
                pagerState = pagerState,
                rootNavController = rootNavController,
                navigationViewModel = navigationViewModel,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreen1Preview() {
    InstaCloneApp3Theme {
        HomeScreen(
            rootNavController = rememberNavController(),
            navigationViewModel = hiltViewModel(),
            pagerState = rememberPagerState(),
            screenStackIndex = 0,
        )
    }
}



