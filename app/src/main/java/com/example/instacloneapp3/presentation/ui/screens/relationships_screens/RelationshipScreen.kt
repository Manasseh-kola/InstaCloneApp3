package com.example.instacloneapp3.presentation.ui.screens.relationships_screens

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

sealed class Screens{
    object Followers: Screens()
    object Following: Screens()
    object Subscriptions: Screens()
}

val screens = listOf(
    Screens.Followers,
    Screens.Following,
    Screens.Subscriptions
)

@Composable
fun RelationShipSelector(width: Float, height: Dp, color: Color, modifier: Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth(width)
            .height(height)
            .background(color)
    ){}
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RelationshipHeader(
    backNavigation: (String, String) -> Unit,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
){

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

    fun onClick(page:Int) {
        coroutineScope.launch {
            // Call scroll to on pagerState
            pagerState.animateScrollToPage(page)
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .clickable(onClick = { backNavigation("profile", "profile") })

            )
            Text(
                text = "${user.user_name}",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ){
            Text(
                color = page0Color.value,
                fontWeight = FontWeight(500),
                text = "${user.followers_count} Followers",
                modifier = Modifier.clickable(onClick = { onClick(0)})
            )
            Text(
                color = page1Color.value,
                fontWeight = FontWeight(500),
                text = "${user.following_count} Following",
                modifier = Modifier.clickable(onClick = { onClick(1)})
            )
            Text(
                color = page2Color.value,
                fontWeight = FontWeight(500),
                text = "5 Subscriptions",
                modifier = Modifier.clickable(onClick = { onClick(2)})
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RelationShipScreen(
    backNavigation: (String, String) -> Unit,
    currentPage: String?
){
    val page = currentPage?.toFloat() ?: 0.00f
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val pagerState = rememberPagerState()
    val xOffset = remember { mutableStateOf(page)}
    val animatedXOffset = animateFloatAsState(targetValue = xOffset.value)
    // scroll to page
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit){
        coroutineScope.launch {
            // Call scroll to on pagerState
            pagerState.scrollToPage(page.toInt())
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        RelationshipHeader(backNavigation, pagerState, coroutineScope)
        Box(
            modifier = Modifier.padding(top = 15.dp, bottom = 5.dp)
        ) {

            RelationShipSelector(1f, 0.5.dp , Color.Gray, Modifier)
            RelationShipSelector((1.0f/3.0f), 1.dp , Color.Black,
                Modifier
                    .offset(x = screenWidth * animatedXOffset.value / 3)
                    .align(Alignment.CenterStart)
            )
        }


        HorizontalPager(
            pageCount = screens.size,
            state = pagerState
        ) {page ->
            Column(modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    xOffset.value = pagerState.startOffsetForPage(page)
                }
            ){
                when (screens[page]) {
                    Screens.Followers -> FollowersScreen(
                        backNavigation = backNavigation,
                        modifier = Modifier
                    )

                    Screens.Following -> FollowingScreen(
                        backNavigation = backNavigation,
                        modifier = Modifier
                    )

                    Screens.Subscriptions -> SubscribeScreen(
                        backNavigation = backNavigation,
                        modifier = Modifier
                    )
                }
            }
        }

    }

}


// ACTUAL OFFSET
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.offsetForPage(page: Int) = currentPage + currentPageOffsetFraction

// OFFSET ONLY FROM THE LEFT
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.startOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtLeast(0f)
}

// OFFSET ONLY FROM THE RIGHT
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.endOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtMost(0f)
}

@Composable
@Preview(showBackground = true)
fun RelationshipScreenPreview(){
    val appstate = rememberAppState()
    InstaCloneApp3Theme {
        RelationShipScreen(appstate::backNavigation, "0")
    }
}