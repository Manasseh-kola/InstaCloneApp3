package com.example.instacloneapp3.presentation.ui.screens.relationships_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

val followersCategories = listOf(
    listOf("Accounts you don't follow back ", "40"),
    listOf("Least Interacted with ", "40"),
    listOf("Hashtags, creators and businesses ", "59"),
)

@Composable
fun FollowersItem(
    following: Posts,
    buttonColor: Color = Color(240, 240, 240),
    textColor: Color = Color.Black,
    text: String = "Remove"
){
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(){
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = following.profile_picture),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = "${following.user_name}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.2f)
                )
                Text(
                    text = "${following.user_name}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.2f)
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            FollowingButton(text = text, width = 0.5f, textColor, buttonColor)
        }
    }
}

@Composable
fun FollowersCategories(followingList: List<Posts>, text: String = "Categories"){
    Column {
        Text(text = text, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
        for(i in 0..3 step 2){
            CategoryItem(followingList[i], followingList[i+1], followersCategories[i/2])
        }
    }
}

@Composable
fun FollowersScreen(
    backNavigation: (String, String) -> Unit,
    modifier: Modifier
){
    val followersList = PostsRepo().getPosts()
    val search = remember{ mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                RelationshipSearchBar(input = search , placeholder = "Search",  width = 0.9f )
                FollowersCategories(followingList = followersList)
                Divider(modifier = Modifier.padding(vertical = 10.dp))
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp)
                ){ Text(text = "All followers", fontWeight = FontWeight.Bold) }

            }

            items(followersList){ follower ->
                FollowersItem(following = follower)
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun FollowersScreenPreview(){
    val appstate = rememberAppState()
    InstaCloneApp3Theme {
        LazyRow()
        {
            item{
                FollowersScreen(
                    appstate::backNavigation, modifier = Modifier
                        .fillParentMaxSize()
                )
            }
        }
    }
}