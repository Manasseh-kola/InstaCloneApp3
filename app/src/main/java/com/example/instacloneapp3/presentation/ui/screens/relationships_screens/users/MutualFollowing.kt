package com.example.instacloneapp3.presentation.ui.screens.relationships_screens.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.CategoryItem
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.FollowingItem
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

@Composable
fun MutualFollowing(){
    val list = PostsRepo().getPosts()
    val followings = list.take(5)
    val suggestions = list
    var otherFollowings = "${list[0].user_name}"
    for(user in list.takeLast(2)){
        otherFollowings = "$otherFollowings,${user.user_name}"
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn() {
            item {
                Text(
                    text = "Followed by",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp, bottom = 10.dp)
                )
            }

            items(followings){ following ->
                FollowingItem(following = following, userProfile = false)
            }


            item{
                CategoryItem(
                    user1 = list[4],
                    user2 = list[6],
                    textInfo = listOf("80 others", otherFollowings)
                )
            }

            item{
                Text(
                    text = "Suggested for you",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp, bottom = 10.dp)
                )
            }

            items(suggestions){ suggestion ->
                FollowingItem(following = suggestion, userProfile = false, isFollowing = false)
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun MutualFollowingPreview(){
    InstaCloneApp3Theme {
        MutualFollowing()
    }
}