package com.example.instacloneapp3.presentation.ui.screens.story_screen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


val emojis = listOf(
    "😂",
    "😯",
    "😍",
    "😢",
    "👏",
    "🔥"

)

@Composable
fun StoryQuickReply() {
    Column(){
        Spacer(Modifier.height(80.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(emojis) { emoji ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 20.dp)
                ) {
                    Text(
                        text = emoji,
                        textAlign = TextAlign.Center,
                        fontSize = 40.sp
                    )
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun StoryQuickReplyPreview(){
    InstaCloneApp3Theme {
        StoryQuickReply()
    }
}


