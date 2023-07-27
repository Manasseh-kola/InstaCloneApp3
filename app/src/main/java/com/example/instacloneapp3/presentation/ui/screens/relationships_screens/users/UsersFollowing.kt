package com.example.instacloneapp3.presentation.ui.screens.relationships_screens.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.FollowingItem
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.RelationshipSearchBar
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme

@Composable
fun UsersFollowing(){
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
@Preview(showBackground = true)
fun UsersFollowingPreview(){
    InstaCloneApp3Theme {
        UsersFollowing()
    }
}