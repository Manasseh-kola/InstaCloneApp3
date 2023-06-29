package com.example.instacloneapp3.presentation.ui.screens.profile_screen.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

/*
User Profile Header
 */

@Composable
fun ProfileDropDown(
    username: String,
    showBottomSheet: MutableState<Boolean>,
    currentBottomSheet: MutableState<BottomSheets>
){
    Row{
        //User Name
        Text(username,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.size(5.dp))

        //DropDown Arrow(Opens Try new account Bottom sheet)
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "",
            modifier = Modifier
                .clickable{
                currentBottomSheet.value = BottomSheets.TRY_NEW_ACCOUNT
                showBottomSheet.value = true
            }
        )

    }
}

@Composable
fun ProfileHeader(
    showBottomSheet: MutableState<Boolean>,
    currentBottomSheet: MutableState<BottomSheets>
) {

    //ProfileHeader Root Composable
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),

    ){
        //User name and DropDown Arrow
        ProfileDropDown(
            username = user.user_name,
            showBottomSheet = showBottomSheet,
            currentBottomSheet = currentBottomSheet
        )


        //Header Create and More Buttons
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){

            //Create Button
            Icon(
                painter = painterResource(id = R.drawable.ic_outlined_add),
                contentDescription = "",
                modifier = Modifier
                    .size(18.dp)
                    .clickable {
                        currentBottomSheet.value = BottomSheets.CREATE_NEW_CONTENT
                        showBottomSheet.value = true
                    }
            )
            Spacer(Modifier.width(20.dp))

            //More button
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = "",
                modifier = Modifier
                    .clickable {
                        currentBottomSheet.value = BottomSheets.MANAGE_USER_ACCOUNT
                        showBottomSheet.value = true
                    }
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ProfileHeaderPreview(){
    InstaCloneApp3Theme() {
        ProfileHeader(
            showBottomSheet = remember{ mutableStateOf(false)},
            currentBottomSheet = remember{ mutableStateOf(BottomSheets.NO_SHEET)}
        )
    }
}