package com.example.instacloneapp3.presentation.ui.screens.home_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostItem(
    post: Posts,
    showModal: MutableState<Boolean>,
    currentModalSheet: MutableState<ModalSheets>,
    currentBottomSheet: MutableState<BottomSheets>,
    showBottomSheet: MutableState<Boolean>,
    currentHomeModal: MutableState<HomeModals>,
    showHomeModal: MutableState<Boolean>,
    currentUser: MutableState<Posts>
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
            showBottomSheet = showBottomSheet,
            modifier = Modifier.fillMaxWidth(),
            profile_picture = post.profile_picture,
            currentBottomSheet = currentBottomSheet,
        ){
            currentUser.value = post
            currentHomeModal.value = HomeModals.USERS_PROFILE_SCREEN
            showHomeModal.value = true
        }

        //Posted Content
        Box(){
            PostItemPager(
                postContent = post.mediaContent,
                pagerState = pagerState
            )

            //PageCount Indicator
            if(isPageCountVisible.value) {
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
                currentBottomSheet = currentBottomSheet,
                pageCount = post.mediaContent.size,
                showBottomSheet = showBottomSheet,
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
    InstaCloneApp3Theme() {
        PostItem(
            post = PostsRepo().getPosts()[0],
            showModal=  remember{ mutableStateOf(false)},
            currentModalSheet = remember{ mutableStateOf(ModalSheets.NO_SHEET)},
            currentBottomSheet = remember{ mutableStateOf(BottomSheets.NO_SHEET)},
            showBottomSheet =  remember{ mutableStateOf(false)},
            currentHomeModal = remember{ mutableStateOf(HomeModals.NO_SCREEN)},
            showHomeModal =  remember{ mutableStateOf(false)},
            currentUser = remember{ mutableStateOf(PostsRepo().getPosts()[0])}
        )
    }
}