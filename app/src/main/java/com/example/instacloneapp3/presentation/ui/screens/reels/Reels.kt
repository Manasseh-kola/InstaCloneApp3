package com.example.instacloneapp3.presentation.ui.screens.reels

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.ui.screens.reels.components.Reel
import com.example.instacloneapp3.presentation.ui.screens.reels.components.ReelsHeader
import com.example.instacloneapp3.presentation.view_models.VideoViewModel


/*
Reels Screen
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelsScreen(
    videoViewModal: VideoViewModel = hiltViewModel()
) {

    val posts = PostsRepo().getPosts()
    val pagerState = rememberPagerState()

    //Reels Screen Root Composable
    Box(
        modifier = Modifier.fillMaxSize()
    ){

        //Reels Pager
        VerticalPager(
            pageCount = posts.size,
            state = pagerState
        ) {page ->
                    Reel(
                    videoViewModal = videoViewModal,
                    post = posts[page],
                    modifier = Modifier
                        .fillMaxSize(),

                )
        }

        //Reels Header
        ReelsHeader(pagerState)
        }
    }


@Composable
@Preview(showBackground = true)
fun ReelsScreenPreview(){
    InstaCloneApp3Theme {
        ReelsScreen()
    }
}