package com.example.instacloneapp3.presentation.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.instacloneapp3.presentation.ui.navigation.destinations.root_destinations.logged_in_destination.LoggedInPager
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

sealed class Screen(val route: String) {
    object LoggedInPager : Screen("loggedInPager")
    object Story : Screen("story")
}

fun NavGraphBuilder.authenticatedGraph(
    rootNavController: NavHostController,
    navigationViewModel: NavigationViewModel
){
    navigation(startDestination = "loggedInPager", route = "authenticated"){
        composable(Screen.LoggedInPager.route){
            LoggedInPager(
                rootNavController = rootNavController,
                navigationViewModel = navigationViewModel,
            )
        }

        composable(Screen.Story.route){}
    }
}






