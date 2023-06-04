package com.example.instacloneapp3.presentation.ui.bottom_sheets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_bottom_sheets.CreateNewContent
import com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_bottom_sheets.manage_account.ManageUserAccount
import com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_bottom_sheets.try_new_account.TryNewAccount

enum class BottomSheets {
    NO_SHEET,
    TRY_NEW_ACCOUNT,
    CREATE_NEW_CONTENT,
    MANAGE_USER_ACCOUNT
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheets(
    sheetState: SheetState,
    showBottomSheet: MutableState<Boolean>,
    currentBottomSheet: MutableState<BottomSheets>
){
    ModalBottomSheet(
        onDismissRequest = { showBottomSheet.value = false},
        sheetState = sheetState
    ) {
        when(currentBottomSheet.value){
            BottomSheets.NO_SHEET -> {}
            BottomSheets.TRY_NEW_ACCOUNT -> TryNewAccount()
            BottomSheets.CREATE_NEW_CONTENT -> CreateNewContent()
            BottomSheets.MANAGE_USER_ACCOUNT -> ManageUserAccount()
        }
    }
}