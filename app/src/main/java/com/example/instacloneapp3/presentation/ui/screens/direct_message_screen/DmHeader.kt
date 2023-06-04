package com.example.instacloneapp3.presentation.ui.screens.direct_message_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

@Composable
fun DmHeader(
    profilePicture: Int,
    userName: String,
    hideModal: () -> Unit,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){

            //Back Button
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .clickable(onClick = {hideModal()})
            )

            //Profile Picture
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = profilePicture),
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(45.dp)
                    .clip(CircleShape)
            )

            //User name and handle
            Column(
            ) {
                Text(text = userName)
                Text(text = "_" + userName + "_")
            }
        }

        //Header Buttons
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .width(90.dp)
        ){
            Icon(imageVector = Icons.Outlined.Phone, contentDescription = "")
            Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "")
            Icon(imageVector = Icons.Outlined.Share, contentDescription = "")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DmHeaderPreview(){
    InstaCloneApp3Theme() {
        DmHeader(
            profilePicture = user.profile_picture,
            userName = user.user_name ,
            hideModal = { /*TODO*/ },
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()

        )
    }
}