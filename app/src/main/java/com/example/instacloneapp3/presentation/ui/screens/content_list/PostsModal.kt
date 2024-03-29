package com.example.instacloneapp3.presentation.ui.screens.content_list

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.screens.home_screen.components.PostFooter
import com.example.instacloneapp3.presentation.ui.screens.home_screen.components.PostHeader
import com.example.instacloneapp3.presentation.ui.screens.home_screen.components.PostItemPager
import com.example.instacloneapp3.presentation.ui.screens.home_screen.components.UserCaption
import com.example.instacloneapp3.presentation.ui.screens.home_screen.components.UserComment
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostItem(
    post: Posts,
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {}

    ){

        PostHeader(
            navigationViewModel = navigationViewModel,
            profile_picture = post.profile_picture,
            user_name = post.user_name,

        ){}

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

        Column(modifier = Modifier.padding(10.dp)){
            PostFooter(
                navigationViewModel = navigationViewModel,
                pageCount = post.mediaContent.size,
                pagerState = pagerState,
            )
            UserCaption(username = post.user_name, caption = post.caption)
            UserComment()
        }
        Divider()


    }
}

@Composable
fun PostsModalHeader(showModal: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){
        //Back Button
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(40.dp)
                .clickable {
                    showModal.value = false
                }
        )

        //User name and Content Category
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = user.user_name)
            Text(text = "Posts", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PostsModal(
    showModal: MutableState<Boolean>,
    modalStartScrollIndex: MutableState<Int>,
    navigationViewModel: NavigationViewModel
) {
    val list1 = PostsRepo().getPosts()
    val posts = list1 + list1 + list1
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    val animatedOffsetX = animateFloatAsState(targetValue = offsetX.value)
    val animatedOffsetY = animateFloatAsState(targetValue = offsetY.value)

    LaunchedEffect(key1 = Unit){
        coroutineScope.launch {
            listState.scrollToItem(modalStartScrollIndex.value)
        }
    }
    Log.i("Drag","${offsetX.value}${offsetY.value}")
    Column(
        modifier = Modifier
            .offset {
                IntOffset(
                    animatedOffsetX.value.roundToInt(),
                    animatedOffsetY.value.roundToInt()
                )
            }
            .background(color = Color.White)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        val xBreakPoint = 114.06748f
                        val yBreakPoint = 283.63824f
                        if (offsetX.value >= xBreakPoint || offsetY.value >= yBreakPoint) {
                            showModal.value = false
                        } else if (offsetX.value != 0.0f && offsetY.value != 0.0f) {
                            offsetX.value = 0.0f
                            offsetY.value = 0.0f
                        }
                    }
                ) { change, dragAmount ->
                    change.consume()
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                }
            }
    ) {
        PostsModalHeader(showModal)
        Divider(Modifier.padding(top = 10.dp))
        LazyColumn( state = listState ){
            items(posts){post ->
                PostItem(
                    post = post,
                    navigationViewModel = navigationViewModel
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun PostsModalPreview(){
    InstaCloneApp3Theme {
        PostsModal(
            navigationViewModel = hiltViewModel(),
            showModal = remember{ mutableStateOf(false)},
            modalStartScrollIndex = remember{ mutableStateOf(0)},
        )
    }
}