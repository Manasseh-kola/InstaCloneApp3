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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.mock_data.User
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

val user = User()

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
    showModal: MutableState<Boolean>,
    navigateToRoute: (String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        item{
            //Add Story
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                        tint = Color(3, 140, 202, 255),
                        contentDescription = "",
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
    showModal: MutableState<Boolean>,
    navigateToRoute: (String) -> Unit,
    showHomeModal: MutableState<Boolean>,
    navigationViewModel: NavigationViewModel,
    currentHomeModal: MutableState<HomeModals>,
    currentModalSheet: MutableState<ModalSheets>,
) {

    val posts = PostsRepo().getPosts()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier.fillMaxWidth(),
    ){
        item{

            //Home Screen Top Bar
            TopBar(
                navigateToRoute = navigateToRoute,
                currentHomeModal = currentHomeModal,
            ){
                showHomeModal.value = true
            }

            //Story LazyList
            StoriesList(
                user = User(),
                stories = posts,
                showModal = showModal,
                navigateToRoute = navigateToRoute,
                currentModalSheet = currentModalSheet,
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
                        showModal = showModal,
                        showHomeModal = showHomeModal,
                        currentHomeModal = currentHomeModal,
                        currentModalSheet = currentModalSheet,
                        navigationViewModel = navigationViewModel,
                    )
                }
            }
        }
    }
}



/*
Home Screen
 */

@Composable
fun HomeScreen(
    navigateToRoute: (String) -> Unit,
    modalState: MutableState<Boolean>,
    navigationViewModel: NavigationViewModel,
    currentModalSheet: MutableState<ModalSheets>,
    ) {


    val showHomeModal = remember{ mutableStateOf(false)}
    val currentHomeModal = remember{ mutableStateOf(HomeModals.NO_SCREEN)}

    Box{
        Column(modifier = Modifier.fillMaxSize()) {
            PostsList(
                showModal = modalState,
                showHomeModal = showHomeModal,
                navigateToRoute = navigateToRoute,
                currentHomeModal = currentHomeModal,
                currentModalSheet = currentModalSheet,
                navigationViewModel = navigationViewModel,
            )
        }

        AnimatedVisibility(
            visible = showHomeModal.value,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            HomeModalScreens(
                showHomeModal = showHomeModal,
                navigateToRoute = navigateToRoute,
                currentHomeModal = currentHomeModal,
                navigationViewModel = navigationViewModel,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreen1Preview() {
    val appState = rememberAppState()
    InstaCloneApp3Theme {
        HomeScreen(
            navigationViewModel = hiltViewModel(),
            modalState = remember{ mutableStateOf(false)},
            navigateToRoute = appState::onNavigateToScreen,
            currentModalSheet = remember{ mutableStateOf(ModalSheets.NO_SHEET)},
        )
    }
}



