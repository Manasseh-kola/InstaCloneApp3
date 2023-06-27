package com.example.instacloneapp3.presentation.ui.screens.reels

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

/*
Owner Of Reel Sound
 */

@Composable
fun SoundOwner(soundOwner: String){
    Row(
        modifier = Modifier
            .fillMaxWidth(0.65f)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0, 0, 0, 50))
            .padding(5.dp),
    ) {
        Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "" , tint = Color.White)
        Spacer(Modifier.width(5.dp))
        Text(
            maxLines = 1,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            text = "$soundOwner - Original audio",
            modifier = Modifier
                .width(150.dp)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun SoundOwnerPreview(){
    InstaCloneApp3Theme() {
        Row(Modifier.fillMaxWidth()){
            SoundOwner(
                soundOwner = "David",
            )
        }
    }
}