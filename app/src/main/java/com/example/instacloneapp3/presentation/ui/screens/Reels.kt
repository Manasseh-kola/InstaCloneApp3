package com.example.instacloneapp3.presentation.ui.screens


import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.components.VideoPlayer
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.calculateCurrentOffsetForPage
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.VideoViewModel
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelsHeader(pagerState: PagerState) {
    var text = if (pagerState.currentPage == 0) "Reels"
    else ""
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = text,
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.size(40.dp)

        )
    }
}

@Composable
fun SoundOwner(userName : String, soundOwner : String, soundOwnerPicture : Int){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "" , tint = Color.White)
        Text(
            "Show Original audio $soundOwner",
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .width(150.dp)
        )
        Icon(imageVector = Icons.Rounded.Person, contentDescription = "", tint = Color.White)
        Text(
            "$userName",
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .width(50.dp)
        )
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(id = soundOwnerPicture),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .clip(RoundedCornerShape(5.dp))
        )
    }
}

@Composable
fun CommentSection(userName: String, profilePictureRes: Int, caption: String, modifier: Modifier){

    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier =  Modifier.padding(vertical = 8.dp)
        ) {
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = profilePictureRes),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)

            )
            Text(
                "$userName",
                modifier = Modifier.padding(horizontal = 10.dp),
                color = Color.White
            )
            Text("Follow",
                color = Color.White,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            )
        }
        Text("$caption", color = Color.White)
        SoundOwner(
            userName = userName,
            soundOwner = userName,
            soundOwnerPicture = profilePictureRes )
    }
}

@Composable
fun EngageItem(value : String, icon : ImageVector){
    Column(
        modifier = Modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = Color.White
        )
        Text("$value", color = Color.White)
    }
}

@Composable
fun EngageSideBar(likes: Int, comment: Int, shares: Int, modifier: Modifier){

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        EngageItem(value = likes.toString(), icon = Icons.Outlined.ThumbUp)
        EngageItem(value = comment.toString(), icon = Icons.Outlined.Email)
        EngageItem(value = shares.toString(), icon = Icons.Outlined.Share)
        Icon(
            tint = Color.White,
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "",
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun Reel(
    post: Posts,
    modifier: Modifier,
    navigateToRoute: (String) -> Unit,
    videoViewModal: VideoViewModel,
){

    Box(
        modifier =  modifier
            .fillMaxSize()
            .background(Color.Black)
            .drawWithContent {
                this.drawContent()
                drawRect(
                    Color.Black.copy(
                        (0.3F)
                    )
                )
            }

    ){


        VideoPlayer()

//        Canvas(modifier = Modifier.fillMaxSize(),
//
//            onDraw = {
//                val size = size
//
//                drawRect(
//                    color = Color.hsv(0F,0F,0F,0.2F),
//                    size = size
//
//                    )
//            })

        CommentSection(
            userName = post.user_name,
            profilePictureRes = post.profile_picture,
            caption = post.caption,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(10.dp)
        )

        val comment = (10..100).random()
        val shares = (10..100).random()
        EngageSideBar(
            likes = post.likes,
            comment = comment,
            shares = shares,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 60.dp)
        )

    }
}


@Composable
fun ReelVideo(){

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelsScreen(
    navigateToRoute: (String) -> Unit,
    backNavigation: (String, String) -> Unit,
    videoViewModal: VideoViewModel = hiltViewModel<VideoViewModel>()
) {
    val pagerState = rememberPagerState()
    val posts = PostsRepo().getPosts()
    val listState = rememberLazyListState()

    Box(
        modifier = Modifier.fillMaxSize()
    ){

        VerticalPager(
            pageCount = posts.size,
            state = pagerState
        ) {page ->
                Reel(
                    posts[page],
                    modifier = Modifier
                        .fillMaxSize(),
                    navigateToRoute,
                    videoViewModal
                )
        }

//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize(),
//            state = listState,
//            flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
//
//        ) {
//
//            items(posts){ post ->
//                Reel(
//                    post,
//                    modifier = Modifier
//                        .fillParentMaxSize()
//                        .fillMaxSize(),
//                    navigateToRoute
//                )
//            }
//
//
//
//        }

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