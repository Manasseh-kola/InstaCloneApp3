package com.example.instacloneapp3.presentation.ui.screens.relationships_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


@Composable
fun SubscribeScreen(
    backNavigation: (String, String) -> Unit,
    modifier: Modifier
){
    val subscribeList = PostsRepo().getPosts()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        LazyColumn(){
            item{
                Text(
                    "Subscribe to creators you follow",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                )
            }
            items(subscribeList){ subscribe ->
                FollowersItem(following = subscribe, Color(3, 109, 202, 201), Color.White, text = "Subscribe")
            }

            item{
                Text(
                    "Suggested Subscriptions",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                )
            }
            items(subscribeList){ subscribe ->
                FollowersItem(following = subscribe, Color(3, 109, 202, 201), Color.White, text = "Subscribe")
            }

        }


    }
}


@Composable
@Preview(showBackground = true)
fun SubscribeScreenPreview(){
    val appsate = rememberAppState()
    InstaCloneApp3Theme {
        LazyRow()
        {
            item{
                SubscribeScreen(appsate::backNavigation, Modifier.fillParentMaxSize())
            }
        }
    }
}

