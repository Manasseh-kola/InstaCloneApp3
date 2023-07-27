package com.example.instacloneapp3.presentation.ui.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.User
import com.example.instacloneapp3.presentation.ui.screens.home_screen.storyImageModifier

/*
Lazy List of Stories in Home/Feeds Screen
 */
@Composable
fun StoriesList(
    user: User,
    stories: List<Posts>,
    rootNavController: NavHostController,
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        item{
            //--Create new Story--
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box {
                    //--User Profile image--
                    Image(
                        painter = painterResource(id = user.profile_picture),
                        contentScale = ContentScale.Crop,
                        contentDescription = "",
                        modifier = storyImageModifier
                            .clickable {
                                rootNavController.navigate("story")
                            }
                    )

                    //--Add Button--
                    Icon(
                        imageVector = Icons.Outlined.AddCircle,
                        tint = Color(3, 140, 202, 255),
                        contentDescription = "",
                        modifier = Modifier
                            .offset(y = 8.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(2.dp)
                            .size(30.dp)
                            .align(Alignment.BottomEnd)
                    )
                }

                //--"Your Story" Text--
                Text(
                    overflow = TextOverflow.Ellipsis,
                    text = "Your Story",
                    maxLines = 1,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )
            }

        }

        items(stories){story->
            StoryItem(
                story = story,
                storyName = story.user_name,
                rootNavController = rootNavController
            )
        }
    }
}
