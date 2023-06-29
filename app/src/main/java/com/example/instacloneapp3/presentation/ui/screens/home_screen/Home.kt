package com.example.instacloneapp3.presentation.ui.screens.home_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.mock_data.User
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

val storyImageModifier = Modifier
    .size(90.dp)
    .clip(CircleShape)

val colorStops = arrayOf(
    0.0f to Color(0xFFd71069),
    0.5f to Color(0xFFe25d6a),
    1f to Color(0xFFe9ad55)
)

@Composable
fun StoriesList(
    user: User,
    stories: List<Posts>,
    modifier: Modifier,
    navigateToRoute: (String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
    drawerState: DrawerState,
    showModal: MutableState<Boolean>,
) {


    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        item{
            //Add Story
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    //User Profile image
                    Image(
                        painter = painterResource(id = user.profile_picture),
                        contentScale = ContentScale.Crop,
                        contentDescription = "",
                        modifier = storyImageModifier
                            .clickable {
                                currentModalSheet.value = ModalSheets.STORY_MODAL
                                showModal.value = true
                            }


                    )

                    //Add Button
                    Icon(
                        imageVector = Icons.Outlined.AddCircle,
                        contentDescription = "",
                        tint = Color(3, 140, 202, 255),
                        modifier = Modifier
                            .offset(y = 8.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(2.dp)
                            .size(30.dp)
                            .align(Alignment.BottomEnd)
                    )
                }

                //User name
                Text(
                    overflow = TextOverflow.Ellipsis,
                    text = "Your Story",
                    maxLines = 1,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )
            }

        }
        items(stories){story->
            StoryItem(
                story = story,
                showModal = showModal,
                storyName = story.user_name,
                navigateToRoute = navigateToRoute,
                currentModalSheet = currentModalSheet,
            )
        }
    }
}



@Composable
fun PostsList(
    posts: List<Posts>,
    feeds:MutableList<Pair<Posts, Int>>,
    modifier: Modifier,
    navigateToRoute: (String) -> Unit,
    backNavigation: (String, String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
    drawerState: DrawerState,
    showModal: MutableState<Boolean>,
    currentBottomSheet: MutableState<BottomSheets>,
    showBottomSheet: MutableState<Boolean>,
    showHomeModal: MutableState<Boolean>,
    currentHomeModal: MutableState<HomeModals>,
    currentUser: MutableState<Posts>
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier.fillMaxWidth(),
    ){
        item{

            //Home Screen Top Bar
            TopBar(
                modalVisible = showModal,
                backNavigation = backNavigation,
                navigateToRoute = navigateToRoute,
                currentHomeModal = currentHomeModal,
                currentModalSheet = currentModalSheet,

            ){
                showHomeModal.value = true
            }

            //Story LazyList
            StoriesList(
                user = User(),
                stories = posts,
                showModal = showModal,
                drawerState = drawerState,
                navigateToRoute = navigateToRoute,
                modifier = Modifier.fillMaxWidth(),
                currentModalSheet = currentModalSheet,
            )
            Divider()
        }

        //Posts/Feeds LazyList
        items(feeds){feed ->

            when(feed.second) {
                0 -> {
                    PostItem(
                        post = feed.first,
                        showModal = showModal,
                        currentUser = currentUser,
                        showHomeModal = showHomeModal,
                        showBottomSheet = showBottomSheet,
                        currentHomeModal = currentHomeModal,
                        currentModalSheet = currentModalSheet,
                        currentBottomSheet = currentBottomSheet,
                    )
                }

                1-> SuggestedReels()
                2-> SuggestedForYou()
            }
        }
    }
}

val user = User()

/*
Home Screen
 */
@Composable
fun HomeScreen(
    navigateToRoute: (String) -> Unit,
    backNavigation: (String, String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
    drawerState: DrawerState,
    modalState: MutableState<Boolean>,
    currentBottomSheet: MutableState<BottomSheets>,
    showBottomSheet: MutableState<Boolean>,
    ) {

    val posts = PostsRepo()
    val showHomeModal = remember{ mutableStateOf(false)}
    val currentUser = remember{ mutableStateOf(posts.getPosts()[0])}
    val currentHomeModal = remember{ mutableStateOf(HomeModals.NO_SCREEN)}


    //Add tag to feeds to display Suggested Reels and Accounts
    val feeds = mutableListOf<Pair<Posts, Int>>()
    posts.getPosts().forEach { feeds.add(Pair(it, 0)) }
    feeds.addAll(feeds)
    feeds[3] = Pair(feeds[0].first, 1)
    feeds[10] = Pair(feeds[0].first, 2)


    Box(){
        Column(modifier = Modifier.fillMaxSize()) {
            PostsList(
                feeds = feeds,
                showModal = modalState,
                posts = posts.getPosts(),
                drawerState = drawerState,
                currentUser = currentUser,
                showHomeModal = showHomeModal,
                backNavigation = backNavigation,
                showBottomSheet = showBottomSheet,
                navigateToRoute = navigateToRoute,
                modifier = Modifier.fillMaxWidth(),
                currentHomeModal = currentHomeModal,
                currentModalSheet = currentModalSheet,
                currentBottomSheet = currentBottomSheet,
            )
        }

        AnimatedVisibility(
            visible = showHomeModal.value,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            HomeModalScreens(
                currentUser = currentUser,
                showHomeModal = showHomeModal,
                showBottomSheet = showBottomSheet,
                navigateToRoute = navigateToRoute,
                currentHomeModal = currentHomeModal,
                currentModalSheet = currentModalSheet,
                currentBottomSheet = currentBottomSheet,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreen1Preview() {
    val appstate = rememberAppState()
    InstaCloneApp3Theme() {
        HomeScreen(
            appstate::onNavigateToScreen,
            appstate::backNavigation,
            remember{ mutableStateOf(ModalSheets.NO_SHEET) },
            rememberDrawerState(initialValue = DrawerValue.Closed),
            remember{ mutableStateOf(false)},
            remember{ mutableStateOf(BottomSheets.NO_SHEET)},
            remember{ mutableStateOf(false)}
        )
    }
}



