package com.example.instacloneapp3.presentation.ui.screens.relationships_screens.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.FollowingButton
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.FollowingItem
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.RelationshipSearchBar
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme



@Composable
fun UsersFollowers(){
    val list = PostsRepo().getPosts()
    val followers = list + list
    val search = remember{ mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                RelationshipSearchBar(input = search, placeholder = "Search", width = 0.9f)
                Spacer(Modifier.height(10.dp))
                UserItem()
            }

            itemsIndexed(followers){i,follower->
                FollowingItem(following = follower, isFollowing = i < 7, userProfile = false)

            }


        }
    }
}

@Composable
fun UserItem(){
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
                painter = painterResource(id = user.profile_picture),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = "${user.user_name}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.3f)
                )
                Text(
                    text = "${user.user_name}",
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.3f)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UsersFollowersPreview(){
    InstaCloneApp3Theme {
        UsersFollowers()
    }
}