package com.example.instacloneapp3.presentation.ui.screens.profile_screen.user

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Stories
import com.example.instacloneapp3.presentation.mock_data.StoriesRepo
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme


/*
Story LazyRow
 */

@Composable
fun StoryHighlightItem(story: Stories) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = story.imageRes),
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Text(
            text = "Category"
        )
    }

}
@Composable
fun StoryHighlightList(stories: List<Stories>) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(),

    ) {
        item{
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                //Create new StoryHighLight Button
                Box {
                    Canvas(modifier = Modifier.size(50.dp),
                        onDraw = {
                            val canvasWidth = size.width
                            val canvasHeight = size.height

                            drawCircle(
                                color = Color.Gray,
                                radius = size.minDimension / 1.9F,
                                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),

                            )

                            drawCircle(
                                color = Color.LightGray,
                                radius = size.minDimension / 2F,
                                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                            )
                        })

                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(30.dp),
                        tint = Color.Gray,

                        )
                }
                Text("New")
            }
        }

        items(stories){story->
            StoryHighlightItem(story = story)
        }
    }
}


@Composable
@Preview(showBackground = false)
fun StoryHighlightsListPreview(){
    InstaCloneApp3Theme {
        StoryHighlightList(stories = StoriesRepo().getStories())
    }
}