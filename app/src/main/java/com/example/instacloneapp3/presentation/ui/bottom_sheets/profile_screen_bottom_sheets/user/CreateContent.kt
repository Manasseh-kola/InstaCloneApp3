package com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_screen_bottom_sheets.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


sealed class NewContent(val text: String, val icon : ImageVector){
    object Reel: NewContent("Reel", Icons.Outlined.PlayArrow)
    object Post: NewContent("Post", Icons.Outlined.AddCircle)
    object Story: NewContent("Story", Icons.Outlined.Add)
    object StoryHighlight: NewContent("StoryHighlight", Icons.Outlined.Menu)
    object Live: NewContent("Live", Icons.Outlined.Send)
    object Guide: NewContent("Guide", Icons.Outlined.Phone)
}

val newContent = listOf(
    NewContent.Reel,
    NewContent.Post,
    NewContent.Story,
    NewContent.StoryHighlight,
    NewContent.Live,
    NewContent.Guide
)

@Composable
fun NewContentItem(text: String, icon: ImageVector){
    Row(
        modifier = Modifier
            .padding(start = 10.dp)
            .fillMaxWidth()

    ){
        Icon(imageVector = icon, contentDescription = "")
        Spacer(Modifier.width(8.dp))
        Text(text = text)
    }
}

@Composable
fun CreateNewContent(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text = "Create",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Divider(Modifier.padding(bottom = 10.dp))

        for (content in newContent){
            NewContentItem(text = content.text, icon = content.icon)
            Divider(Modifier.padding(vertical = 10.dp))
        }
        Spacer(Modifier.height(40.dp))
    }
}


@Composable
@Preview(showBackground = true)
fun CreateNewContentPreview(){
    InstaCloneApp3Theme {
        CreateNewContent()
    }
}