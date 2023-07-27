package com.example.instacloneapp3.presentation.ui.screens.notifications_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
Header for Notification screen
 */
@Composable
fun NotificationsScreenHeader(navigateBack: ()->Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
    ){
        //--Back Button to navigate back--
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(40.dp)
                .clickable {
                    navigateBack()
                }
        )

        //--"Notifications Text"--
        Text(
            text = "Notifications",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
