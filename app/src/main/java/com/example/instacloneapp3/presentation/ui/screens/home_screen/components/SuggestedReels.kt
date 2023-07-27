package com.example.instacloneapp3.presentation.ui.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme

/*
Suggested Reels for User
 */

@Composable
fun ReelItem(reel: Posts) {

    val configuration = LocalConfiguration.current
    Column(modifier = Modifier) {
        Image(
            painter = painterResource(id = reel.profile_picture),
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier
                .width( configuration.screenWidthDp.dp / 3 )
                .height( configuration.screenHeightDp.dp / 3 )
                .clip( RoundedCornerShape(6.dp) )
        )
    }

}

@Composable
fun SuggestedReels(){
    val list = PostsRepo().getPosts()
    val reels = list + list
    Column( modifier = Modifier.fillMaxWidth() ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ){
            Text(text = "Suggested reels")
            Row{
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = ""
                )
                Spacer(Modifier.width(8.dp))
                Text(text = "Watch all")
            }
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ){
            items(reels){reel ->
                ReelItem(reel)
            }
        }

    }

}

@Composable
@Preview(showBackground = true)
fun SuggestedReelsPreview(){
    InstaCloneApp3Theme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            SuggestedReels()
        }
    }
}