package com.example.instacloneapp3.presentation.ui.screens.home_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.mock_data.User
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel
import kotlinx.coroutines.launch

/*
LazyList for Feeds
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserFeedsList(
    rootNavController: NavHostController,
    navigationViewModel: NavigationViewModel,
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()

    //--Function to scroll to Messages Screen(Horizontal-Pager navigation)--
    fun scrollToPage(page: Int){
        coroutineScope.launch{
            pagerState.animateScrollToPage(page)
        }
    }

    val posts = PostsRepo().getPosts()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier.fillMaxWidth(),
    ){
        item{
            //--Home Screen Top Bar--
            TopBar( navigationViewModel = navigationViewModel ){ scrollToPage(1) }

            //--Story LazyList--
            StoriesList(
                user = User(),
                stories = posts,
                rootNavController = rootNavController,
            )

            Divider()
        }

        //Posts/Feeds LazyList
        itemsIndexed(posts + posts){ index, feed ->
            when(index) {
                3 -> SuggestedReels()
                10 -> SuggestedForYou()
                else -> {
                    PostItem(
                        post = feed,
                        navigationViewModel = navigationViewModel,
                    )
                }
            }
        }
    }
}

