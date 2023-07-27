package com.example.instacloneapp3.presentation.ui.navigation.destinations.root_destinations.logged_in_destination

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.instacloneapp3.presentation.ui.navigation.components.InstagramCloneNavBar
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
NavBar
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavBarDestination(
    rootNavController: NavHostController,
    navigationViewModel: NavigationViewModel = hiltViewModel(),
    pagerState: PagerState
) {
    val navBarState = rememberState()
    val navController = navBarState.navController
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val screenXOffsetSet = remember { mutableStateOf(false) }

    Scaffold(
        //--Custom Bottom Navigation Component--
        bottomBar = {
            InstagramCloneNavBar(
                onNavigate = navBarState::onBottomNavBarNavigation,
                navigationViewModel = navigationViewModel,
                navBackStackEntry = navBackStackEntry
            )
        },
        modifier = Modifier
            .onGloballyPositioned { layoutCoordinates ->
                //--Set screenXOffset if not set--
                if (!screenXOffsetSet.value) {
                    val rect = layoutCoordinates.boundsInRoot()
                    navigationViewModel.setScreenXOffset(rect.topRight.x)
                    screenXOffsetSet.value = true
                }
            }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "bottomNavBar",
            modifier = Modifier
                .padding(innerPadding)
        ) {
            bottomNavBarGraph(
                pagerState = pagerState,
                rootNavController = rootNavController,
                navigationViewModel = navigationViewModel
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun AppPreview(){
    InstaCloneApp3Theme {
        NavBarDestination(
            pagerState = rememberPagerState(),
            rootNavController =  rememberNavController(),
        )
    }
}