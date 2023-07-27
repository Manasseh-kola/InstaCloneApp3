package com.example.instacloneapp3.presentation.ui.screens.relationships_screens.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.FollowingItem
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme


@Composable
fun SuggestedForYou(){
    val list = PostsRepo().getPosts()
    val suggestions = list + list
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LazyColumn(){
            items(suggestions){ suggestion ->
                FollowingItem(following = suggestion, userProfile = false, isFollowing = false)
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun SuggestForYouPreview(){
    InstaCloneApp3Theme {
        SuggestedForYou()
    }
}