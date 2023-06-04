package com.example.instacloneapp3.presentation.ui.screens.direct_message_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Messages
import com.example.instacloneapp3.presentation.mock_data.Posts

@Composable
fun Messages(
    messages: List<Messages>,
    modifier: Modifier,
    receiver: Posts
){
    LazyColumn(
        reverseLayout = true,
        contentPadding = PaddingValues(top = 5.dp, start = 8.dp, end = 8.dp),
        modifier = modifier
    ) {
        items(messages) { message ->
            if (message.sent) {
                MessageSenderItem(
                    message = message,
                    modifier = Modifier
                )
            } else {
                MessageReceiverItem(
                    message = message,
                    modifier = Modifier,
                    receiver = receiver
                )
            }
        }
    }

}