package com.example.instacloneapp3.presentation.ui.screens.home_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.screens.NotificationsScreen
import com.example.instacloneapp3.presentation.ui.screens.favourites_screen.FavouritesScreen
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.UsersProfileScreen

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
    showBottomSheet: MutableState<Boolean>,
    currentBottomSheet: MutableState<BottomSheets>,
    currentModalSheet: MutableState<ModalSheets>,
    currentUser: MutableState<Posts>
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
                navigateToRoute = navigateToRoute,
                showBottomSheet = showBottomSheet,
                currentBottomSheet = currentBottomSheet,
                currentModalSheet = currentModalSheet,
                currentHomeModal = currentHomeModal,
                showHomeModal = showHomeModal,
                currentUser = currentUser
            )
        }
        HomeModals.FOLLOWING -> {}
        HomeModals.MESSAGES -> {}
        HomeModals.NO_SCREEN -> {}

    }
}