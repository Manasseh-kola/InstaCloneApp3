package com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_screen_bottom_sheets.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.ui.core.components.SwitchButton
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme


val notificationTypes = listOf(
    "Posts",
    "Stories",
    "Reels",
    "Videos"
)


@Composable
fun UsersProfileNotifications(){
    val checked = remember{ mutableStateOf(true) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Text(
            text = "Notifications",
            fontSize = 20.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier
                .padding(10.dp)
        )

        Divider()
        for(notificationType in notificationTypes){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = notificationType,
                    fontSize = 18.sp,
                )
                SwitchButton(32)
            }
            Divider()
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ){
            Text(
                text = "Live videos",
                fontSize = 18.sp,
            )
            Row(){
                Text(text = "Some", fontSize = 18.sp)
                Spacer(Modifier.width(15.dp))
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
            }

        }

        Divider(Modifier.padding(bottom = 8.dp))
        Text(
            color = Color.Gray,
            text = "Receive notifications when user shares photos or videos.",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, bottom = 10.dp)
        )
    }
}



@Composable
@Preview(showBackground = true)
fun UsersProfileNotificationPreview(){
    InstaCloneApp3Theme {
        UsersProfileNotifications()
    }
}