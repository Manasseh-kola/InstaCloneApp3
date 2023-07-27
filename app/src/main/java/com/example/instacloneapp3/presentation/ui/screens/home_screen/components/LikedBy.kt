package com.example.instacloneapp3.presentation.ui.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme


@Composable
fun LikedBy(
    imageSize: Dp = 28.dp,
    imageSpacing: Dp = 40.dp,
    borderWidth: Dp = 2.dp,
    borderColor: Color = Color.White
){
    val likedBy = PostsRepo().getPosts().take(3)
    Row(

    ){

        Box(
        ) {

            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(likedBy[0].profile_picture),
                contentDescription = "Liked By",
                modifier = Modifier
                    .padding(start = imageSpacing)
                    .size(imageSize)
                    .clip(CircleShape)
                    .border(borderWidth, borderColor, CircleShape),

            )

            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(likedBy[1].profile_picture),
                contentDescription = "Liked By",
                modifier = Modifier
                    .padding(start = imageSpacing/2)
                    .size(imageSize)
                    .clip(CircleShape)
                    .border(borderWidth, borderColor, CircleShape)
            )

            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(likedBy[2].profile_picture),
                contentDescription = "Liked By",
                modifier = Modifier
                    .padding(start = 0.dp)
                    .size(imageSize)
                    .clip(CircleShape)
                    .border(borderWidth, borderColor, CircleShape)

            )

            }
        }
    }


@Composable
@Preview(showBackground = true)
fun LikedByPreview(){
    InstaCloneApp3Theme {
        LikedBy()
    }
}
