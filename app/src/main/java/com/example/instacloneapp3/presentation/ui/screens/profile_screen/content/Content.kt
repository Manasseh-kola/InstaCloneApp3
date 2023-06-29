package com.example.instacloneapp3.presentation.ui.screens.profile_screen.content

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.RelationShipSelector
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.startOffsetForPage
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
User Content
Contains
- Posts Lazy Grid
- Reels Lazy Grid
- Tagged Lazy Grid
 */

val colorStops = arrayOf(
    0.0f to Color(0,0,0,50),
    1f to Color(0,0,0,0),
)

sealed class ContentScreens(val icon : ImageVector){
    object PostedContent: ContentScreens(icon  = Icons.Filled.Person)
    object ReelsContent: ContentScreens(icon = Icons.Filled.PlayArrow)
    object TaggedContent: ContentScreens(icon = Icons.Rounded.AccountBox)
}

val contentScreens = listOf(
    ContentScreens.PostedContent,
    ContentScreens.ReelsContent,
    ContentScreens.TaggedContent
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostItem(
    thumbnailImage: Int,
    index: Int,
    showModal: MutableState<Boolean>,
    modalStartScrollIndex: MutableState<Int>,
    transformOriginOffset: MutableState<Offset>
){

    Box{
        Image(
            painter = painterResource(id = thumbnailImage),
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier
                .size(150.dp)
                .clickable {
                    modalStartScrollIndex.value = index
                    showModal.value = true
                }
        )

        //Gradient Overlay for visibility of white texts
        Canvas(
            onDraw = {
                val size = size
                drawRect(
                    size = size,
                    color = Color(0f,0f,0f,0.14f),
                )
            },
            modifier = Modifier
                .size(150.dp)
        )
    }
}

@Composable
fun MultipleContentIndicator(modifier: Modifier){
    Box(modifier = modifier){
        Column(
            modifier = Modifier
                .offset(x = 3.dp, y = 3.dp)
                .size(15.dp)
                .clip(RoundedCornerShape(5.dp))
                .border(2.dp, Color.White, RoundedCornerShape(5.dp))
        ){}

        Column(
            modifier = Modifier
                .size(15.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color.White)
        ){}

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostNavigationBar(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
) {

    //Function to navigate to content category
    fun onClick(page:Int) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(page)
        }
    }

    // Content Category Icons
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 50.dp),

    ){
        for( page in contentScreens.indices ){
            Icon(
                contentDescription = null,
                imageVector = contentScreens[page].icon,
                modifier = Modifier.clickable(onClick = {onClick(page)}),
                tint = if(page == pagerState.currentPage) Color.Black else Color.Gray

            )
        }
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
            //Current Page Indicator
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
                               showModal = showModal,
                               scrollState = scrollState,
                               gridState = postsGridState,
                               modalStartScrollIndex = modalStartScrollIndex,
                               transformOriginOffset = transformOriginOffset
                           )
                       }

                       ContentScreens.ReelsContent -> {
                           currentContent.value = "reelsContent"
                           ReelsContent(
                               showModal = showModal,
                               scrollState = scrollState,
                               gridState = reelsGridState,
                               modalStartScrollIndex = modalStartScrollIndex,
                               transformOriginOffset = transformOriginOffset
                           )
                       }

                       ContentScreens.TaggedContent -> {
                           currentContent.value = "taggedContent"
                           TaggedContent(
                               showModal = showModal,
                               scrollState = scrollState,
                               gridState = taggedGridState,
                               modalStartScrollIndex = modalStartScrollIndex,
                               transformOriginOffset = transformOriginOffset
                           )
                       }
                   }
               }
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
            scrollState = scrollState,
            postsGridState = rememberLazyGridState(),
            reelsGridState = rememberLazyGridState(),
            taggedGridState = rememberLazyGridState(),
            showModal = remember{ mutableStateOf(false)},
            modalStartScrollIndex = remember{ mutableStateOf(0)},
            currentContent = remember{ mutableStateOf("postsContent") },
            transformOriginOffset = remember{ mutableStateOf(Offset(0f,0f))}
        )
    }
}
