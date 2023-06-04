package com.example.instacloneapp3.presentation.ui.screens.direct_message_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Messages
import com.example.instacloneapp3.presentation.mock_data.Posts


@Composable
fun MessageReceiverItem(
    message: Messages,
    modifier: Modifier,
    receiver: Posts
){
    Row(
        verticalAlignment = Alignment.Bottom
    ){
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(id = receiver.profile_picture),
            contentDescription = "",
            modifier = Modifier
                .padding(end = 10.dp)
                .size(26.dp)
                .clip(CircleShape)
        )
        Row(
            modifier = modifier
                .padding(top = 4.dp, bottom = 4.dp, end = 40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.LightGray)
                .padding(8.dp)
        ) {
            Text(
                "${message.message}",
                color = Color.Black
            )
        }
    }
}