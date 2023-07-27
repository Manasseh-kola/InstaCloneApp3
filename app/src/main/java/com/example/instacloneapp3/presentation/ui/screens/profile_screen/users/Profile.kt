package com.example.instacloneapp3.presentation.ui.screens.profile_screen.users

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.components.HorizontalDraggableScreen
import com.example.instacloneapp3.presentation.ui.screens.home_screen.components.SuggestedForYou
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.content.UserContent
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.content.ContentScreens
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.components.ProfileHeader
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.components.ProfileInfoTab
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.components.StoryHighlights
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
Users Profile Screen
 */
@Composable
fun UsersProfileScreen(
    currentUser: Posts,
    screenStackIndex: Int,
    navigationViewModel: NavigationViewModel,
){
    val configuration = LocalConfiguration.current
    val scrollState = rememberScrollState()
    val postsGridState = rememberLazyGridState()
    val reelsGridState = rememberLazyGridState()
    val taggedGridState = rememberLazyGridState()

    val suggestionDropDown = remember{ mutableStateOf(false)}
    val modalStartScrollIndex = remember{ mutableStateOf(0)}
    val showModal = remember{ mutableStateOf(false)}
    val scrollEnabled = remember { mutableStateOf(true)}
    val currentContent: MutableState<ContentScreens> = remember{ mutableStateOf(ContentScreens.PostedContent)}
    val transformOriginOffset = remember{ mutableStateOf(Offset(0f,0f))}


    val postScrollDisabled = remember { derivedStateOf { postsGridState.firstVisibleItemScrollOffset > 0 } }

    val reelsScrollDisabled = remember { derivedStateOf { reelsGridState.firstVisibleItemScrollOffset > 0 } }

    val taggedScrollDisabled = remember { derivedStateOf { taggedGridState.firstVisibleItemScrollOffset > 0 } }

    when(currentContent.value){
        ContentScreens.PostedContent -> { scrollEnabled.value = !postScrollDisabled.value }
        ContentScreens.ReelsContent -> { scrollEnabled.value = !reelsScrollDisabled.value }
        ContentScreens.TaggedContent -> { scrollEnabled.value = !taggedScrollDisabled.value }
    }


    HorizontalDraggableScreen(
        screenStackIndex = screenStackIndex,
        navigationViewModel = navigationViewModel
    ) {
        Surface(shadowElevation = 10.dp){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(
                        state = scrollState,
                        enabled = scrollEnabled.value
                    )
            ) {
                //--Profile Header--
                ProfileHeader(
                    navigationViewModel = navigationViewModel,
                    userName = currentUser.user_name
                )

                ProfileInfoTab(
                    profileOwner = currentUser,
                    suggestionDropDown = suggestionDropDown,
                    navigationViewModel = navigationViewModel
                )

                AnimatedVisibility(visible = suggestionDropDown.value) {
                    SuggestedForYou(configuration.screenWidthDp.dp * 0.4f)
                    Spacer(Modifier.height(10.dp))
                }

                StoryHighlights()

                UserContent(
                    showModal = showModal,
                    scrollState = scrollState,
                    postsGridState = postsGridState,
                    reelsGridState = reelsGridState,
                    currentContent = currentContent,
                    taggedGridState = taggedGridState,
                    modalStartScrollIndex = modalStartScrollIndex,
                    transformOriginOffset = transformOriginOffset
                )
            }
        }
    }
}









@Composable
@Preview(showBackground = true)
fun UsersProfileScreenPreview(){

    InstaCloneApp3Theme {
        UsersProfileScreen(
            navigationViewModel = hiltViewModel(),
            currentUser = PostsRepo().getPosts()[0],
            screenStackIndex = 0,
        )
    }
}
