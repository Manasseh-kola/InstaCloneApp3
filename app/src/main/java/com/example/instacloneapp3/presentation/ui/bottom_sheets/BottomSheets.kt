package com.example.instacloneapp3.presentation.ui.bottom_sheets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.presentation.ui.bottom_sheets.home_screen_bottom_sheets.PostItemMore
import com.example.instacloneapp3.presentation.ui.bottom_sheets.home_screen_bottom_sheets.ShareContent
import com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_screen_bottom_sheets.user.CreateNewContent
import com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_screen_bottom_sheets.user.manage_account.ManageUserAccount
import com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_screen_bottom_sheets.user.try_new_account.TryNewAccount
import com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_screen_bottom_sheets.users.UsersProfileNotifications
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

enum class BottomSheets {

    //User Profile
    NO_SHEET,
    TRY_NEW_ACCOUNT,
    CREATE_NEW_CONTENT,
    MANAGE_USER_ACCOUNT,

    //Home Screen
    POST_ITEM_MORE,
    SHARE_CONTENT,

    //Users Profile
    USERS_PROFILE_NOTIFICATIONS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheets(
    sheetState: SheetState,
    navigationViewModel: NavigationViewModel = hiltViewModel<NavigationViewModel>()
){
    val navigationUiState = navigationViewModel.navigationState.collectAsState()
    ModalBottomSheet(
        onDismissRequest = { navigationViewModel.closeBottomSheet()},
        sheetState = sheetState
    ) {
        when(navigationUiState.value.currentBottomSheet){
            BottomSheets.NO_SHEET -> {}
            BottomSheets.TRY_NEW_ACCOUNT -> TryNewAccount()
            BottomSheets.CREATE_NEW_CONTENT -> CreateNewContent()
            BottomSheets.MANAGE_USER_ACCOUNT -> ManageUserAccount()
            BottomSheets.POST_ITEM_MORE -> PostItemMore()
            BottomSheets.SHARE_CONTENT -> ShareContent()
            BottomSheets.USERS_PROFILE_NOTIFICATIONS -> {
                UsersProfileNotifications()}
        }
    }
}