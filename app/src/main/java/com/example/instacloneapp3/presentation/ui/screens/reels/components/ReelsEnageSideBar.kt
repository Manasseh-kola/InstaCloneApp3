package com.example.instacloneapp3.presentation.ui.screens.reels.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme

/*
SideBar for Reels Screen
*/

@Composable
fun EngageItem(
    value : String = "",
    icon : ImageVector
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp),
    ) {
        //Icon for Engage Item
        Icon(
            tint = Color.White,
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier
                .size(30.dp)

        )

        //Number for Engagements
        if(value != "") {
            Text(
                text ="$value",
                color = Color.White
            )
        }
    }
}

@Composable
fun EngageSideBar(
    likes: Int,
    comment: Int,
    shares: Int,
    modifier: Modifier,
    soundOwnerPicture: Int
)
{
    //Root Element of side bar
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Like Reel
        EngageItem(value = likes.toString(), icon = Icons.Outlined.ThumbUp)

        //Comment
        EngageItem(value = comment.toString(), icon = Icons.Outlined.Email)

        //Share
        EngageItem(value = shares.toString(), icon = Icons.Outlined.Share)

        //More
        EngageItem(value = "", icon = Icons.Outlined.MoreVert)

        //Original Sound Owner image
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(id = soundOwnerPicture),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 10.dp)
                .size(30.dp)
                .clip(RoundedCornerShape(5.dp))
                .border(2.dp, Color.White, RoundedCornerShape(5.dp))
        )
    }
}


@Composable
@Preview(showBackground = true)
fun EngageSideBarPreview(){
    InstaCloneApp3Theme() {
        Column(
            modifier  = Modifier.background(Color.Black)
        ) {
            EngageSideBar(
                likes = 100,
                comment = 20,
                shares = 40,
                modifier = Modifier,
                soundOwnerPicture = user.profile_picture
            )
        }
    }
}