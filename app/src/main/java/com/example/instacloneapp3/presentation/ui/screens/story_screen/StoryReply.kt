package com.example.instacloneapp3.presentation.ui.screens.story_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

@Composable
fun ReplyStory(
    modifier: Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)

        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var textValue = remember { mutableStateOf("")}

        //Text Input
        BasicTextField(
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            value = textValue.value,
            onValueChange = { textValue.value = it },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            shape = RoundedCornerShape(20.dp),
                            color = Color.White,
                            width = 1.dp
                        )
                        .padding(horizontal = 15.dp, vertical = 12.dp)

                ) {
                    if (textValue.value.isEmpty()) {
                        Text("Send Message", color = Color.White)
                    }
                    innerTextField()
                }
            },
            modifier = Modifier
                .weight(8F)
        )

        //Like Button
        Icon(
            painter = painterResource(id = R.drawable.ic_outlined_favorite),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .weight(1F)
                .size(26.dp)
        )

        //Share Button
        Icon(
            painter = painterResource(id = R.drawable.ic_dm),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .weight(1F)
                .size(26.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun StoryReplyPreview(){
    InstaCloneApp3Theme() {
        ReplyStory(modifier = Modifier)
    }
}