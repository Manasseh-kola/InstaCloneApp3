package com.example.instacloneapp3.presentation.states

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes

data class NavigationStates(

    //--Logged In Horizontal pager current page--
    val loggedInPagerPage: Int = 0,

    //--Bottom Sheets--
    val currentUser: Posts? = null,
    val showBottomSheet: Boolean = false,
    val currentScreen: AppScreenTypes = AppScreenTypes.Home(),
    val currentBottomSheet: BottomSheets = BottomSheets.NO_SHEET,

    /*
    Back Stack Handling
    */
    //--Lazy Composition for Prev Screen--
    val showPrevScreen: Boolean = false,

    //--Horizontal Draggable Screen offsets--
    val prevScreenIndex: Int = -1,
    val screenXOffset:Float = 0.0f,
    val topScreenXOffset: Float = 0.0f,
    val prevScreenXOffset: Float = 0.0f,

    //--Current Root element to select current Stack--
    val currentStackRoot: AppScreenTypes = AppScreenTypes.Home(),

    //--Screen Back Stacks--
    val currentStack: SnapshotStateList<AppScreenTypes> = mutableStateListOf(AppScreenTypes.Home()),

    val homeStack: SnapshotStateList<AppScreenTypes> = mutableStateListOf(AppScreenTypes.Home()),
    val reelsStack: SnapshotStateList<AppScreenTypes> = mutableStateListOf(AppScreenTypes.Reels()),
    val newPostStack: SnapshotStateList<AppScreenTypes> = mutableStateListOf(AppScreenTypes.New),
    val searchStack: SnapshotStateList<AppScreenTypes> = mutableStateListOf(AppScreenTypes.Search()),
    val messagesStack: SnapshotStateList<AppScreenTypes> = mutableStateListOf(AppScreenTypes.Messages()),
    val favoritesStack: SnapshotStateList<AppScreenTypes> = mutableStateListOf(AppScreenTypes.FavoriteFeeds),
    val userProfileStack: SnapshotStateList<AppScreenTypes> = mutableStateListOf(AppScreenTypes.UserProfile()),
    val usersProfileStack: SnapshotStateList<AppScreenTypes> = mutableStateListOf(AppScreenTypes.UsersProfile(args = "args")),
)
