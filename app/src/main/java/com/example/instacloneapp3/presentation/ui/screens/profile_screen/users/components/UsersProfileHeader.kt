package com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

@Composable
fun ProfileHeader(
    navigationViewModel: NavigationViewModel,
    userName: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){

        //Back Button
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.CenterStart)
                .clickable {
                    //--NavigateBack--
                    navigationViewModel.popBackStack()
                }
        )

        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            text = userName,
            modifier = Modifier
                .align(Alignment.Center)
        )

        Row(
            modifier = Modifier.align(Alignment.CenterEnd)
        ){

            //Notifications Header Button
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navigationViewModel.openBottomSheet(
                            currentBottomSheet = BottomSheets.USERS_PROFILE_NOTIFICATIONS,
                            currentScreen = AppScreenTypes.UsersProfile(
                                args = null,
                                screenIndex = null
                            )
                        )
                    }
            )


            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}