package com.example.instacloneapp3.presentation.ui.screens.notifications_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
Monitor Ads
 */
@Composable
fun AdsActivity() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()

    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(50.dp)
            )
            Column {
                Text(
                    text = "Ads",
                    fontWeight = FontWeight(500)
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Recent Activity from you ads.",
                    color = Color.Gray
                )

            }
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.Gray,
            modifier = Modifier
                .size(30.dp)
        )
    }
    Divider(Modifier.padding(bottom = 10.dp))
    Text(
        text = "This week",
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp,
        modifier = Modifier.padding(start = 10.dp)
    )
}

