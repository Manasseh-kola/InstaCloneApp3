package com.example.instacloneapp3.presentation.ui.navigation.destinations.root_destinations.logged_in_destination

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.ui.navigation.destinations.non_root_destinations.messages_destination.MessagesDestination
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
Horizontal pager for Bottom Navigation bar destination and Messages
 */
val screens = mutableListOf(
    AppScreenTypes.Home(),
    AppScreenTypes.Messages()
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoggedInPager(
    rootNavController: NavHostController,
    navigationViewModel: NavigationViewModel = hiltViewModel()
){
    val navigationUiState = navigationViewModel.navigationState.collectAsState()


    //--Enable Scroll only when on Home Destination in Navbar destination or on Messages Destination--
    val userScrollEnabled = (
            !navigationUiState.value.currentStack.isEmpty() &&
            (navigationUiState.value.currentStack[0] is AppScreenTypes.Home ||
            navigationUiState.value.currentStack[0] is AppScreenTypes.Messages)
    )

    val pagerState = rememberPagerState()
    LaunchedEffect(key1 = pagerState.currentPage){
        navigationViewModel.selectStack(screens[pagerState.currentPage])
    }


    HorizontalPager(
        userScrollEnabled = userScrollEnabled,
        pageCount = screens.size,
        state = pagerState
    ) { page ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {}
        ){
            when (screens[page]) {

                //--NavBar Destination--
                is AppScreenTypes.Home ->{
                    NavBarDestination(
                        rootNavController = rootNavController,
                        navigationViewModel = navigationViewModel,
                        pagerState = pagerState
                    )
                }

                //--Messages Destination--
                else -> {
                    MessagesDestination(
                        navigationViewModel = navigationViewModel,
                        pagerState = pagerState
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun HomePagerPreview() {
    InstaCloneApp3Theme {
        LoggedInPager(
            rootNavController = rememberNavController()
        )
    }
}