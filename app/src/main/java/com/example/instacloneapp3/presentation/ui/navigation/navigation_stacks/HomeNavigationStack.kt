package com.example.instacloneapp3.presentation.ui.navigation.navigation_stacks

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.navigation.graphs.HomeScreens
import com.example.instacloneapp3.presentation.ui.navigation.graphs.Screens
import com.example.instacloneapp3.presentation.ui.screens.home_screen.HomeScreen
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.user.ProfileScreen
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.UsersProfileScreen
import com.example.instacloneapp3.presentation.ui.screens.reels.ReelsScreen
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel


@Composable
fun HomeScreensMapper(
    destination: Screens,
    navigateToRoute: (String) -> Unit,
    modalState: MutableState<Boolean>,
    navigationViewModel: NavigationViewModel,
    backNavigation: (String, String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
){
    when(destination){
        HomeScreens.Home -> {
            HomeScreen(
                modalState = modalState,
                navigateToRoute = navigateToRoute,
                currentModalSheet = currentModalSheet,
                navigationViewModel = navigationViewModel,
            )
        }
        HomeScreens.Reels -> {
            ReelsScreen(
                backNavigation =backNavigation,
                navigateToRoute = navigateToRoute,
            )
        }
        HomeScreens.AdTools -> TODO()
        HomeScreens.UserProfile -> {
            ProfileScreen(
                navigateToRoute = navigateToRoute,
                currentModalSheet = currentModalSheet,
                navigationViewModel = navigationViewModel,
            )
        }
        HomeScreens.UsersProfile -> {}
        HomeScreens.Notifications -> TODO()
        HomeScreens.FavoritesFeeds -> TODO()
        HomeScreens.FollowingFeeds -> TODO()
    }
}


@Composable
fun HomeScreensEntryPoint(
    navigateToRoute: (String) -> Unit,
    modalState: MutableState<Boolean>,
    navigationViewModel: NavigationViewModel,
    backNavigation: (String, String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
){

    val navigationUiState = navigationViewModel.customNavStackStates.collectAsState()
    val backStack = navigationUiState.value.homeStack

    //Add Home Screen as first element to stack
    if (backStack.size == 0){
        navigationViewModel.addRouteToBackStack(HomeScreens.Home)
    }

    //Display Last two Screens
    Box {
        when( backStack.size){
            1 ->{
                HomeScreensMapper(
                    modalState = modalState,
                    backNavigation = backNavigation,
                    navigateToRoute = navigateToRoute,
                    currentModalSheet = currentModalSheet,
                    navigationViewModel = navigationViewModel,
                    destination = backStack[backStack.lastIndex],

                )
            }
            else ->{
                HomeScreensMapper(
                    modalState = modalState,
                    backNavigation = backNavigation,
                    navigateToRoute = navigateToRoute,
                    currentModalSheet = currentModalSheet,
                    navigationViewModel = navigationViewModel,
                    destination = backStack[backStack.lastIndex-1],
                )
                HomeScreensMapper(
                    modalState = modalState,
                    backNavigation = backNavigation,
                    navigateToRoute = navigateToRoute,
                    currentModalSheet = currentModalSheet,
                    navigationViewModel = navigationViewModel,
                    destination = backStack[backStack.lastIndex],
                )
            }
        }
    }
}

