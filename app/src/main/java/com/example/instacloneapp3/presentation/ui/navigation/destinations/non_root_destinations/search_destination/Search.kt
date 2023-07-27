package com.example.instacloneapp3.presentation.ui.navigation.destinations.non_root_destinations.search_destination

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.user.ProfileScreen
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.UsersProfileScreen
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
Search Screen Entry-Point/Root
 */
@Composable
fun SearchDestination(
    rootNavController: NavController,
    navigationViewModel: NavigationViewModel
){

    val navigationUiState = navigationViewModel.navigationState.collectAsState()
    val searchBackStack = navigationUiState.value.searchStack
    val posts = PostsRepo().getPosts()


    for((i, screen) in searchBackStack.withIndex()){
        when(screen){

            //--Reels Screen--
            is AppScreenTypes.Reels -> {
                AnimatedVisibility(visible = i >= searchBackStack.size - 2) {}
            }

            //--UserProfile Screen--
            is AppScreenTypes.UserProfile -> {
                AnimatedVisibility(visible = i >= searchBackStack.size - 2) {
                    ProfileScreen(navigationViewModel = navigationViewModel, screenStackIndex = i)
                }
            }

            //--UsersProfile Screen--
            is AppScreenTypes.UsersProfile -> {
                val userIndex = searchBackStack[i].args!!.toInt()
                val currentUser = posts[userIndex]
                AnimatedVisibility(visible = i >= searchBackStack.size - 2) {
                    UsersProfileScreen(
                        navigationViewModel = navigationViewModel,
                        currentUser = currentUser,
                        screenStackIndex = i
                    )
                }
            }

            //--Search Screen--
            else -> {
                AnimatedVisibility(visible = i >= searchBackStack.size - 2) {}
            }
        }
    }
}