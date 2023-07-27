package com.example.instacloneapp3.presentation.ui.screens.notifications_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Posts

/*
Items for Notifications Lazy List
 */
@Composable
fun NotificationItem( activity: Posts ) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ){
        Row{
            //--User Profile Picture--
            Image(
                painter = painterResource(id = activity.profile_picture),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(10.dp))
            Text(
                buildAnnotatedString{
                    append("${activity.user_name} who you might know" +
                            " is on instagram.")
                    withStyle(style = SpanStyle(color = Color.Gray)){
                        append("${(1..5).random()}d")
                    }

                },
                modifier = Modifier.fillMaxWidth(0.6f)
            )
        }

        //--Action Button--
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0.129f, 0.588f, 0.953f, 1.0f))
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .clickable { }
            ){
            Text(
                text = "Follow",
                color = Color.White,
                fontWeight = FontWeight(500)
            )
        }
    }
}