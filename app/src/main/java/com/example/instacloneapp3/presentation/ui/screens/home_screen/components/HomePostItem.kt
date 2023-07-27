package com.example.instacloneapp3.presentation.ui.screens.home_screen.components

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
import androidx.compose.runtime.collectAsState
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
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel
import kotlinx.coroutines.delay

/*
Feeds/Home Lazy List Item
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostItem(
    post: Posts,
    navigationViewModel: NavigationViewModel,
) {

    //--Navigation State--
    val navigationUiState = navigationViewModel.navigationState.collectAsState()

    //--Horizontal Pager state for multiple posts--
    val pagerState = rememberPagerState()
    val userIndex = PostsRepo().getPosts().indexOfFirst{ it.user_name == post.user_name}

    //--State of Page count Indicator--
    val isPageCountVisible = remember{ mutableStateOf(true) }
    LaunchedEffect(key1 = Unit){
        delay(5000)
        isPageCountVisible.value = false
    }


    Column( modifier = Modifier.fillMaxWidth() ){

        //--Header containing User profile picture and name--
        PostHeader(
            user_name = post.user_name,
            profile_picture = post.profile_picture,
            navigationViewModel = navigationViewModel
        ){
            navigationViewModel.addCurrentUser(post)
            navigationViewModel.pushToBackStack(
                AppScreenTypes.UsersProfile(
                    args = userIndex.toString(),
                    screenIndex = navigationUiState.value.currentStack.size,
                )
            )
        }

        //--Posted Content--
        Box {
            PostItemPager(
                postContent = post.mediaContent,
                pagerState = pagerState
            )

            //--PageCount Indicator--
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

        //--Post Footer and Comment section--
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
@Preview(showBackground = true)
fun PostItemPreview(){
    InstaCloneApp3Theme {
        PostItem(
            post = PostsRepo().getPosts()[0],
            navigationViewModel = hiltViewModel(),
        )
    }
}