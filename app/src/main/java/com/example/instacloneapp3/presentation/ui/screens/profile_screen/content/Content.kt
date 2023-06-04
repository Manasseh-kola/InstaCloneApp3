package com.example.instacloneapp3.presentation.ui.screens.profile_screen.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.RelationShipSelector
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.startOffsetForPage
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class ContentScreens{
    PostedContent,
    ReelsContent,
    TaggedContent
}

val contentScreens = listOf(
    ContentScreens.PostedContent,
    ContentScreens.ReelsContent,
    ContentScreens.TaggedContent
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostNavigationBar(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
) {

    fun onClick(page:Int) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(page)
        }
    }

    val page0Color =  animateColorAsState(
        if(pagerState.currentPage == 0) Color.Black
        else Color.Gray
    )

    val page1Color =  animateColorAsState(
        if(pagerState.currentPage == 1) Color.Black
        else Color.Gray
    )

    val page2Color =  animateColorAsState(
        if(pagerState.currentPage == 2) Color.Black
        else Color.Gray
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "",
            modifier = Modifier.clickable(onClick = {onClick(0)}),
            tint = page0Color.value
        )
        Icon(
            imageVector = Icons.Rounded.PlayArrow,
            contentDescription = "",
            modifier = Modifier.clickable(onClick = {onClick(1)}),
            tint = page1Color.value
        )
        Icon(
            imageVector = Icons.Rounded.AccountBox,
            contentDescription = "",
            modifier = Modifier.clickable(onClick = {onClick(2)}),
            tint = page2Color.value
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserContent(
    scrollState: ScrollState,
    postsGridState: LazyGridState,
    reelsGridState: LazyGridState,
    taggedGridState: LazyGridState,
    currentContent: MutableState<String>,
    showModal: MutableState<Boolean>,
    modalStartScrollIndex: MutableState<Int>,
    transformOriginOffset: MutableState<Offset>

) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val xOffset = remember { mutableStateOf(0.00f) }
    val animatedXOffset = animateFloatAsState(targetValue = xOffset.value)



    Column(
        modifier = Modifier.height(configuration.screenHeightDp.dp-50.dp)
    ){
        PostNavigationBar(pagerState, coroutineScope = coroutineScope)
        Box(
            modifier = Modifier.padding(bottom = 2.dp)
        ) {

            RelationShipSelector(1f, 0.5.dp , Color.Gray, Modifier)
            RelationShipSelector((1.0f/3.0f), 1.dp , Color.Black,
                Modifier
                    .offset(x = screenWidth * animatedXOffset.value / 3)
                    .align(Alignment.CenterStart)
            )
        }

        HorizontalPager(
            pageCount = contentScreens.size,
            state = pagerState
        ) {page->
           Box(){
               Column(modifier = Modifier
                   .graphicsLayer {
                       xOffset.value = pagerState.startOffsetForPage(page)
                   }
               ){
                   when (contentScreens[page]) {
                       ContentScreens.PostedContent -> {
                           currentContent.value = "postsContent"
                           PostedContent(
                               postsGridState,
                               scrollState,
                               showModal,
                               modalStartScrollIndex,
                               transformOriginOffset
                           )
                       }

                       ContentScreens.ReelsContent -> {
                           currentContent.value = "reelsContent"
                           ReelsContent(
                               reelsGridState,
                               scrollState,
                               showModal,
                               modalStartScrollIndex,
                               transformOriginOffset
                           )
                       }

                       ContentScreens.TaggedContent -> {
                           currentContent.value = "taggedContent"
                           TaggedContent(
                               taggedGridState,
                               scrollState,
                               showModal,
                               modalStartScrollIndex,
                               transformOriginOffset
                           )
                       }
                   }
               }
//               if (scrollState.value != 1023) {
//                   Column(modifier = Modifier
//                       .fillMaxSize()
//                       .background(color = Color.Transparent)
//                       .clickable(onClick = {})
//                   ){
//
//                   }
//               }
           }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun UserContentPreview(){
    val scrollState = rememberScrollState()
    InstaCloneApp3Theme {
        UserContent(
            scrollState,
            rememberLazyGridState(),
            rememberLazyGridState(),
            rememberLazyGridState(),
            remember{ mutableStateOf("postsContent") },
            remember{ mutableStateOf(false)},
            remember{ mutableStateOf(0)},
            remember{ mutableStateOf(Offset(0f,0f))}
        )
    }
}
