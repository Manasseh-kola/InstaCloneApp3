package com.example.instacloneapp3.presentation.ui.screens.home_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.navigation.graphs.AppScreens
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostItem(
    post: Posts,
    showModal: MutableState<Boolean>,
    currentModalSheet: MutableState<ModalSheets>,
    navigationViewModel: NavigationViewModel
) {

    //Pager state for multiple posts
    val pagerState = rememberPagerState()

    //State of Page count Indicator
    val isPageCountVisible = remember{ mutableStateOf(true) }
    LaunchedEffect(key1 = Unit){
        delay(5000)
        isPageCountVisible.value = false
    }

    //Root composable
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        // Header containing User profile and name
        PostHeader(
            user_name = post.user_name,
            profile_picture = post.profile_picture,
            navigationViewModel = navigationViewModel
        ){
            navigationViewModel.addCurrentUser(post)
            navigationViewModel.addRouteToBackStack(AppScreens.UsersProfile)
        }

        //Posted Content
        Box {
            PostItemPager(
                postContent = post.mediaContent,
                pagerState = pagerState
            )

            //PageCount Indicator
            if(isPageCountVisible.value && post.mediaContent.size > 1) {
                Text(
                    text = "${pagerState.currentPage + 1}/${post.mediaContent.size}",
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.TopEnd)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Gray)
                        .padding(horizontal = 5.dp, vertical = 3.dp)
                )
            }
        }

        //Post Footer and Comment section
        Column(modifier = Modifier.padding(10.dp)){
            PostFooter(
                navigationViewModel = navigationViewModel,
                pageCount = post.mediaContent.size,
                pagerState = pagerState,

            )
            UserCaption(username = post.user_name, caption = post.caption)
            UserComment(showModal = showModal, currentModalSheet = currentModalSheet)
        }

        Divider()
    }
}


@Composable
@Preview(showBackground = true)
fun PostItemPreview(){
    InstaCloneApp3Theme {
        PostItem(
            post = PostsRepo().getPosts()[0],
            navigationViewModel = hiltViewModel(),
            showModal=  remember{ mutableStateOf(false)},
            currentModalSheet = remember{ mutableStateOf(ModalSheets.NO_SHEET)},
        )
    }
}