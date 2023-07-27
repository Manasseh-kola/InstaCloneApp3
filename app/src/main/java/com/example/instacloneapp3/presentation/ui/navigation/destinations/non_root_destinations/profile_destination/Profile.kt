package com.example.instacloneapp3.presentation.ui.navigation.destinations.non_root_destinations.profile_destination

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.user.ProfileScreen
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.UsersProfileScreen
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.RelationShipScreen
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
Profile Screen Entry-Point/Root
 */
@Composable
fun ProfileDestination(
    navigationViewModel: NavigationViewModel
){
    val navigationUiState = navigationViewModel.navigationState.collectAsState()
    val profileBackStack = navigationUiState.value.userProfileStack
    val posts = PostsRepo().getPosts()

    Box{
        for ((i, screen) in profileBackStack.withIndex()) {
            when (screen) {

                //--Reels Screen--
                is AppScreenTypes.Reels -> {
                    AnimatedVisibility(visible = i >= profileBackStack.size - 2) {}
                }


                //--Users Profile Screen--
                is AppScreenTypes.UsersProfile -> {
                    val userIndex = profileBackStack[i].args!!.toInt()
                    val currentUser = posts[userIndex]
                    AnimatedVisibility(visible = i >= profileBackStack.size - 2) {
                        UsersProfileScreen(
                            navigationViewModel = navigationViewModel,
                            currentUser = currentUser,
                            screenStackIndex = i
                        )
                    }
                }

                //--Users Relationship screen--
                is AppScreenTypes.UserRelationShip -> {
                    RelationShipScreen(
                        currentPage = profileBackStack[profileBackStack.lastIndex].args!!,
                        navigationViewModel = navigationViewModel,
                        screenStackIndex = i
                    )
                }

                //--User Profile Screen--
                else -> {
                    val dragEnabled = profileBackStack.size > 1
                    AnimatedVisibility(visible = i >= profileBackStack.size - 2) {
                        ProfileScreen(
                            navigationViewModel = navigationViewModel,
                            dragEnabled = dragEnabled,
                            screenStackIndex = i,
                        )
                    }
                }
            }
        }
    }

}