package com.example.instacloneapp3.presentation.ui.screens.direct_message_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Messages
import com.example.instacloneapp3.presentation.mock_data.MessagesRepo
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme

@Composable
fun MessageSenderItem(
    modifier: Modifier,
    message: Messages
){
    Row(){
        Spacer(modifier = Modifier.weight(0.8F))
        Row(
            modifier = modifier
                .padding(top = 4.dp, bottom = 4.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color(3, 109, 202, 201))
                .padding(8.dp)
                .weight(1F)
        ) {
            Text(
                "${message.message}",
                color = Color.White
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MessageSenderItemPreview(){
    val messages = MessagesRepo().getMessages()
    InstaCloneApp3Theme {
        MessageSenderItem(Modifier,messages[0])
    }
}