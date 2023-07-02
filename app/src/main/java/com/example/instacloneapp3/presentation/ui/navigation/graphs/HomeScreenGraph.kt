package com.example.instacloneapp3.presentation.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.screens.favourites_screen.FavouritesScreen
import com.example.instacloneapp3.presentation.ui.screens.home_screen.HomeScreen
import com.example.instacloneapp3.presentation.ui.screens.notifications_screen.NotificationsScreen
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.user.ProfileScreen
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.UsersProfileScreen
import com.example.instacloneapp3.presentation.ui.screens.reels.ReelsScreen
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel


sealed class AppScreens(val route: String, val navArgs: String? = null){
    object Home : AppScreens("home")
    object Reels : AppScreens("reels")
    object AdTools: AppScreens("adTools")
    object UserProfile : AppScreens("userProfile")
    object UsersProfile: AppScreens("usersProfile")
    object Notifications: AppScreens("notifications")
    object FollowingFeeds: AppScreens("followingFeeds")
    object FavoritesFeeds: AppScreens("favoritesFeeds")
}

@Composable
fun HomeScreensMapper(
    destination: AppScreens,
    navigateToRoute: (String) -> Unit,
    modalState: MutableState<Boolean>,
    navigationViewModel: NavigationViewModel,
    backNavigation: (String, String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
){
    when(destination){
        AppScreens.Home -> {
            HomeScreen(
                modalState = modalState,
                navigateToRoute = navigateToRoute,
                currentModalSheet = currentModalSheet,
                navigationViewModel = navigationViewModel,
            )
        }
        AppScreens.Reels -> {
            ReelsScreen(
                backNavigation = backNavigation,
                navigateToRoute = navigateToRoute,
            )
        }
        AppScreens.AdTools -> TODO()
        AppScreens.UserProfile -> {
            ProfileScreen(
                navigateToRoute = navigateToRoute,
                currentModalSheet = currentModalSheet,
                navigationViewModel = navigationViewModel,
            )
        }
        AppScreens.UsersProfile -> {

            UsersProfileScreen(
                navigateToRoute = navigateToRoute,
                navigationViewModel = navigationViewModel,
            )

        }
        AppScreens.Notifications -> {
            NotificationsScreen { navigationViewModel.popBackStack()}
        }
        AppScreens.FavoritesFeeds -> {
            FavouritesScreen { navigationViewModel.popBackStack()}
        }
        AppScreens.FollowingFeeds -> {

        }
    }
}
