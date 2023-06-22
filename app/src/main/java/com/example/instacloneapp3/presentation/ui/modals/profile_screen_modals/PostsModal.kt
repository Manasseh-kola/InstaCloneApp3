package com.example.instacloneapp3.presentation.ui.modals.profile_screen_modals

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.screens.home_screen.PostFooter
import com.example.instacloneapp3.presentation.ui.screens.home_screen.PostHeader
import com.example.instacloneapp3.presentation.ui.screens.home_screen.UserCaption
import com.example.instacloneapp3.presentation.ui.screens.home_screen.UserComment
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostItem(
    post: Posts,
    showModal: MutableState<Boolean>,
    currentModalSheet: MutableState<ModalSheets>,
    currentBottomSheet: MutableState<BottomSheets>,
    showBottomSheet: MutableState<Boolean>,
) {

    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {

            }

    ){

        PostHeader(
            post.profile_picture,
            post.user_name,
            modifier = Modifier.fillMaxWidth(),
            currentBottomSheet,
            showBottomSheet
        )
        Box(){
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = post.imageRes),
                contentDescription = "",
                modifier = Modifier
                    .size(450.dp)

            )
        }
        Column(modifier = Modifier.padding(10.dp)){
            PostFooter(
                currentBottomSheet,
                showBottomSheet,
                pagerState
            )
            UserCaption(username = post.user_name, caption = post.caption)
            UserComment(showModal, currentModalSheet)
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

        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("${user.user_name}")
            Text("Posts", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PostsModal(
    showModal: MutableState<Boolean>,
    modalStartScrollIndex: MutableState<Int>,
    currentModalSheet: MutableState<ModalSheets>,
    currentBottomSheet: MutableState<BottomSheets>,
    showBottomSheet: MutableState<Boolean>
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
        LazyColumn(
            state = listState
        ){
            items(posts){post ->
                PostItem(
                    post = post,
                    showModal,
                    currentModalSheet,
                    currentBottomSheet,
                    showBottomSheet,
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun PostsModalPreview(){
    InstaCloneApp3Theme() {
        PostsModal(
            remember{ mutableStateOf(false)},
            remember{ mutableStateOf(0)},
            remember{ mutableStateOf(ModalSheets.NO_SHEET)},
            remember{ mutableStateOf(BottomSheets.NO_SHEET)},
            remember{ mutableStateOf(false)}
        )
    }
}