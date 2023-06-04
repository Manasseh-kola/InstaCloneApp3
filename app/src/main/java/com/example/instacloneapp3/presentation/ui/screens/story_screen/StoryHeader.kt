package com.example.instacloneapp3.presentation.ui.screens.story_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

@Composable
fun StoryHeader(
    hideModal: () -> Unit,
){
    Column(){
        Spacer(modifier = Modifier.padding(vertical = 13.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {

            //Profile Info and time posted
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(130.dp)

            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = user.profile_picture),
                    contentDescription = "",
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                )
                Text(text = "${user.user_name}", color = Color.White)
                Text(text = "23h", color = Color.White)
            }

            //Header Icons
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {


                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "",
                    tint = Color.White
                )

                //Close Story Screen
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable(onClick = { hideModal() })
                )



            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StoryHeaderPreview(){
    InstaCloneApp3Theme() {
        StoryHeader(){}
    }
}