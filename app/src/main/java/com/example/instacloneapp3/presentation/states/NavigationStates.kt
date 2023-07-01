package com.example.instacloneapp3.presentation.states

import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes

data class NavigationStates(
    val currentUser: Posts? = null,
    val showBottomSheet: Boolean = false,
    val currentScreen: AppScreenTypes = AppScreenTypes.Home,
    val currentBottomSheet: BottomSheets = BottomSheets.NO_SHEET,
)
