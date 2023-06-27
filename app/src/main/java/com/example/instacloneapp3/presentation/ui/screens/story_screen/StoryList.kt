package com.example.instacloneapp3.presentation.ui.screens.story_screen

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.calculateCurrentOffsetForPage
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoryListScreen(hideModal: ()-> Unit){
    val stories = PostsRepo().getPosts()


    val pagerState = rememberPagerState()
    // scroll to page
    val coroutineScope = rememberCoroutineScope()
    val currentStory = remember{ mutableStateOf(0)}

    LaunchedEffect(key1 = currentStory.value){
        coroutineScope.launch {
            // Call scroll to on pagerState
            pagerState.scrollToPage(currentStory.value)
        }
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ){
        HorizontalPager(
            pageCount = stories.size,
            state = pagerState
        ) {page ->

            Column(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
                        val offScreenRight = pageOffset < 0f
                        val deg = 20f
                        val interpolated = FastOutLinearInEasing.transform(pageOffset.absoluteValue)
                        rotationY = interpolated * if (offScreenRight) deg else -deg

                        transformOrigin = TransformOrigin(
                            pivotFractionX = if (offScreenRight) 0f else 1f,
                            pivotFractionY = 0.5f
                        )

                    }
                    .drawWithContent {
                        val pageOffset = pagerState.calculateCurrentOffsetForPage(page)

                        this.drawContent()
                        drawRect(
                            Color.Black.copy(
                                (pageOffset.absoluteValue * .7f)
                            )
                        )
                    }
            ){
                StoryScreen(hideModal){
                    if(currentStory.value != stories.lastIndex){
                        currentStory.value +=1
                    }else{
                        hideModal()
                    }

                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun StoryListScreenPreview(){
    val appstate = rememberAppState()
    InstaCloneApp3Theme() {
        StoryListScreen(appstate::hideModal)
    }
}