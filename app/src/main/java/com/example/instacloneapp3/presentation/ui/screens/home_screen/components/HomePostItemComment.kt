package com.example.instacloneapp3.presentation.ui.screens.home_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme

/*
Post Item Comment Section
 */
@Composable
fun UserComment() {

    //Root Composable
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
    ){
        //Text/button to view all comments on posted content
        Text(
            "View all comments",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier
                .clickable{}
        )

        Spacer(Modifier.height(8.dp))

        //Add Comment Button
        AnimatedVisibility(
            visible = true,
            enter = expandVertically(),
            exit = shrinkVertically(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    painter = painterResource(id = user.profile_picture),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)
                )
                Spacer(Modifier.width(2.dp))
                Text("   Add comment...", fontSize = 10.sp, color = Color.Gray)

            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun UserCommentPreview(){
    InstaCloneApp3Theme {
        UserComment()
    }
}