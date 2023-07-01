package com.example.instacloneapp3.presentation.ui.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
Home Post Item Header
 */
@Composable
fun PostHeader(
    profile_picture: Int,
    user_name: String,
    modifier: Modifier,
    navigationViewModel:NavigationViewModel,
    onclick: ()-> Unit
){

    //Root Composable
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .fillMaxWidth(),
    ){
        //Post Owner Info
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){
            //Post owner profile picture
            Image(
                painter = painterResource(id = profile_picture),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { }
            )

            Spacer(Modifier.width(10.dp))

            //Post owner user name
            Text(
                text = user_name,
                modifier = Modifier
                    .clickable { onclick() }
            )
        }

        //More Button
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "",
            modifier = Modifier.clickable {
                navigationViewModel.openBottomSheet(
                    BottomSheets.POST_ITEM_MORE,
                    AppScreenTypes.Home,
                )
            }
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PostHeaderPreview(){
    InstaCloneApp3Theme() {
        PostHeader(
            profile_picture = user.profile_picture,
            navigationViewModel = hiltViewModel(),
            user_name = "David",
            modifier = Modifier,
        ) {}
    }
}