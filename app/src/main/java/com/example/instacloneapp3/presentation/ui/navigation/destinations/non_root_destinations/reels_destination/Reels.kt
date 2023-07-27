package com.example.instacloneapp3.presentation.ui.navigation.destinations.non_root_destinations.reels_destination

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.user.ProfileScreen
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.UsersProfileScreen
import com.example.instacloneapp3.presentation.ui.screens.reels.ReelsScreen
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
Reels Screen Entry-Point/Root
 */
@Composable
fun ReelsDestination(
    rootNavController: NavController,
    navigationViewModel: NavigationViewModel
){
    val navigationUiState = navigationViewModel.navigationState.collectAsState()
    val reelsBackStack = navigationUiState.value.reelsStack
    val posts = PostsRepo().getPosts()


    Box{
        for ((i, screen) in reelsBackStack.withIndex()) {
            when (screen) {

                //--User Profile Screen--
                is AppScreenTypes.UserProfile -> {
                    AnimatedVisibility(visible = i >= reelsBackStack.size - 2) {
                        ProfileScreen(
                            navigationViewModel = navigationViewModel,
                            screenStackIndex = i
                        )
                    }
                }

                //--Users Profile Screen--
                is AppScreenTypes.UsersProfile -> {
                    val userIndex = reelsBackStack[i].args!!.toInt()
                    val currentUser = posts[userIndex]
                    AnimatedVisibility(visible = i >= reelsBackStack.size - 2) {
                        UsersProfileScreen(
                            navigationViewModel = navigationViewModel,
                            currentUser = currentUser,
                            screenStackIndex = i
                        )
                    }
                }

                //--Reels Screen--
                else -> {
                    AnimatedVisibility(visible = i >= reelsBackStack.size - 2) {
                        ReelsScreen()
                    }
                }
            }
        }
    }
}