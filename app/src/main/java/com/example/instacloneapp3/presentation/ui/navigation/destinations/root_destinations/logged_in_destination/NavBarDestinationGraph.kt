package com.example.instacloneapp3.presentation.ui.navigation.destinations.root_destinations.logged_in_destination

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.navigation.destinations.non_root_destinations.home_destination.HomeDestination
import com.example.instacloneapp3.presentation.ui.navigation.destinations.non_root_destinations.profile_destination.ProfileDestination
import com.example.instacloneapp3.presentation.ui.navigation.destinations.non_root_destinations.reels_destination.ReelsDestination
import com.example.instacloneapp3.presentation.ui.screens.search_screen.SearchScreen
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel


@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.bottomNavBarGraph(
    rootNavController: NavHostController,
    navigationViewModel: NavigationViewModel,
    pagerState: PagerState
) {
    navigation(startDestination = "home", route = "bottomNavBar"){
        composable(AppScreenTypes.Home().route){
            HomeDestination(
                pagerState = pagerState,
                rootNavController = rootNavController,
                navigationViewModel = navigationViewModel
            )
        }

        composable(AppScreenTypes.Search().route){
            SearchScreen()
        }

        composable(AppScreenTypes.New.route){
            Row { Text(text = "Add new") }
        }

        composable(AppScreenTypes.Reels().route){
            ReelsDestination(
                rootNavController = rootNavController ,
                navigationViewModel = navigationViewModel
            )
        }
        
        composable(AppScreenTypes.UserProfile().route){
            ProfileDestination(navigationViewModel = navigationViewModel)
        }
    }
}




