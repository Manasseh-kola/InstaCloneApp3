package com.example.instacloneapp3.presentation.ui.screens.home_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.screens.notifications_screen.NotificationsScreen
import com.example.instacloneapp3.presentation.ui.screens.favourites_screen.FavouritesScreen
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.UsersProfileScreen
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

enum class HomeModals{
    NO_SCREEN,
    NOTIFICATIONS_SCREEN,
    FAVOURITES_SCREEN,
    FOLLOWING,
    MESSAGES,
    USERS_PROFILE_SCREEN

}


@Composable
fun HomeModalScreens(
    currentHomeModal: MutableState<HomeModals>,
    showHomeModal: MutableState<Boolean>,
    navigateToRoute: (String) -> Unit,
    navigationViewModel: NavigationViewModel,
){
    when(currentHomeModal.value){
        HomeModals.NOTIFICATIONS_SCREEN -> {
            NotificationsScreen() {
                showHomeModal.value = false
            }
        }
        HomeModals.FAVOURITES_SCREEN -> {
            FavouritesScreen() {
                showHomeModal.value = false
            }
        }
        HomeModals.USERS_PROFILE_SCREEN -> {
            UsersProfileScreen(
                navigationViewModel = navigationViewModel,
                navigateToRoute = navigateToRoute,
                showHomeModal = showHomeModal,
            )
        }
        HomeModals.FOLLOWING -> {}
        HomeModals.MESSAGES -> {}
        HomeModals.NO_SCREEN -> {}

    }
}