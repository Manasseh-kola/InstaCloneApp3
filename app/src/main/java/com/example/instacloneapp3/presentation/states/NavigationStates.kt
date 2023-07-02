package com.example.instacloneapp3.presentation.states

import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.navigation.graphs.AppScreens

data class NavigationStates(

    //Bottom Sheets
    val currentUser: Posts? = null,
    val showBottomSheet: Boolean = false,
    val currentScreen: AppScreens = AppScreens.Home,
    val currentBottomSheet: BottomSheets = BottomSheets.NO_SHEET,

    //Back Stack Handling
    val newScreenOnStack:Boolean = false,
    val updateStack: Boolean = false,
    val currentStackRoot: AppScreens = AppScreens.Home,
    val homeStack: MutableList<AppScreens> = mutableListOf(),
    val currentStack: MutableList<AppScreens> = mutableListOf(),


)
