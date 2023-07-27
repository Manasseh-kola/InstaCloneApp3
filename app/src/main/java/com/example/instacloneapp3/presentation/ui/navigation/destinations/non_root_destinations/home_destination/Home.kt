package com.example.instacloneapp3.presentation.ui.navigation.destinations.non_root_destinations.home_destination

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.screens.home_screen.HomeScreen
import com.example.instacloneapp3.presentation.ui.screens.notifications_screen.NotificationsScreen
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.UsersProfileScreen
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.RelationShipScreen
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.users.UsersRelationShipScreen
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
Home/Feeds Screen Entry Point
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeDestination(
    pagerState: PagerState,
    rootNavController: NavHostController,
    navigationViewModel: NavigationViewModel,

){

    val navigationUiState = navigationViewModel.navigationState.collectAsState()
    val homeBackStack = navigationUiState.value.homeStack
    val posts = PostsRepo().getPosts()

    LaunchedEffect(key1 = Unit){
        navigationViewModel.selectStack(AppScreenTypes.Home())
    }

    Box {
        for ((i, screen) in homeBackStack.withIndex()) {
            when (screen) {

                //--Reels Screen--
                is AppScreenTypes.Reels -> {
                    AnimatedVisibility(visible = i >= homeBackStack.size - 2) {}
                }

                //--User Profile Screen--
                is AppScreenTypes.UserProfile -> {
                    AnimatedVisibility(visible = i >= homeBackStack.size - 2) {}
                }

                //--Users Profile Screen--
                is AppScreenTypes.UsersProfile -> {
                    val userIndex = homeBackStack[i].args!!.toInt()
                    val currentUser = posts[userIndex]
                    AnimatedVisibility(visible = i >= homeBackStack.size - 2){
                        UsersProfileScreen(
                            screenStackIndex = i,
                            currentUser = currentUser,
                            navigationViewModel = navigationViewModel
                        )
                    }
                }

                //--Favorite Feeds Screen--
                is AppScreenTypes.FavoriteFeeds -> {
                    AnimatedVisibility(
                        visible = i >= homeBackStack.size - 2) {}
                }

                //--Notifications Screen--
                is AppScreenTypes.Notifications -> {
                    AnimatedVisibility(visible = i >= homeBackStack.size - 2) {
                        NotificationsScreen (
                            screenStackIndex = i,
                            navigationViewModel = navigationViewModel
                        )
                    }
                }

                //--User Relationship Screen--
                is AppScreenTypes.UserRelationShip -> {
                    RelationShipScreen(
                        screenStackIndex = i,
                        currentPage = homeBackStack[i].args!!,
                        navigationViewModel = navigationViewModel
                    )
                }

                //--Users Relationship Screen--
                is AppScreenTypes.UsersRelationShip -> {
                    val userIndex = homeBackStack[i].userIndex
                    AnimatedVisibility(visible = i >= homeBackStack.size - 2){
                        UsersRelationShipScreen(
                            screenStackIndex = i,
                            userIndex = userIndex!!,
                            currentPage = homeBackStack[i].args!!,
                            navigationViewModel = navigationViewModel
                        )
                    }
                }

                //--Home Screen--
                else -> {
                    AnimatedVisibility(visible = i >= homeBackStack.size - 2) {
                        HomeScreen(
                            screenStackIndex = i,
                            pagerState = pagerState,
                            rootNavController = rootNavController,
                            navigationViewModel = navigationViewModel
                        )
                    }
                }
            }
        }
    }
}