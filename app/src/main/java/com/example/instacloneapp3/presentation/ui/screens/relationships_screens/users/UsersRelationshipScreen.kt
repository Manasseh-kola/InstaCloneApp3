package com.example.instacloneapp3.presentation.ui.screens.relationships_screens.users

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
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.startOffsetForPage
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

sealed class Screens(val name: String){
    object Mutual: Screens("Mutual")
    object Followers: Screens("Followers")
    object Following: Screens("Following")
    object SuggestedForYou: Screens("For you")

}

val screens = listOf(
    Screens.Mutual,
    Screens.Followers,
    Screens.Following,
    Screens.SuggestedForYou
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
    user: Posts,
){

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
                    .clickable(onClick = { backNavigation("home", "home") })

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

            screens.forEachIndexed{ page, screen ->
                Text(
                    color = if(pagerState.currentPage == page) Color.Black else Color.Gray,
                    fontWeight = FontWeight(500),
                    text = screen.name,
                    modifier = Modifier
                        .clickable{ onClick(page)}
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersRelationShipScreen(
    backNavigation: (String, String) -> Unit,
    currentPage: String?,
    userIndex: Int = 0
){
    val user = PostsRepo().getPosts()[userIndex]
    val page = currentPage?.toFloat() ?: 0.00f
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val pagerState = rememberPagerState()
    val xOffset = remember { mutableStateOf(page) }
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
        RelationshipHeader(
            backNavigation,
            pagerState,
            coroutineScope,
            user
        )
        Box(
            modifier = Modifier.padding(top = 15.dp, bottom = 5.dp)
        ) {

            RelationShipSelector(1f, 0.5.dp , Color.Gray, Modifier)
            RelationShipSelector((1.0f/4.0f), 1.dp , Color.Black,
                Modifier
                    .offset(x = screenWidth * animatedXOffset.value / 4)
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
                    Screens.Mutual -> MutualFollowing()

                    Screens.Followers -> UsersFollowers()

                    Screens.Following -> UsersFollowing()

                    Screens.SuggestedForYou -> SuggestedForYou()

                }
            }
        }

    }

}

@Composable
@Preview(showBackground = true)
fun RelationshipScreenPreview(){
    val appstate = rememberAppState()
    InstaCloneApp3Theme {
        UsersRelationShipScreen(
            backNavigation = appstate::backNavigation,
            currentPage = "0"
        )
    }
}