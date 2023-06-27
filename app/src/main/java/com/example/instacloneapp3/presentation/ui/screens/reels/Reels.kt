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
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.VideoViewModel


/*
Reels Screen
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelsScreen(
    navigateToRoute: (String) -> Unit,
    backNavigation: (String, String) -> Unit,
    videoViewModal: VideoViewModel = hiltViewModel<VideoViewModel>()
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
                    navigateToRoute = navigateToRoute,
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
    val appState = rememberAppState()
    InstaCloneApp3Theme() {
        ReelsScreen(
            appState::onNavigateToScreen,
            appState::backNavigation
        )
    }
}