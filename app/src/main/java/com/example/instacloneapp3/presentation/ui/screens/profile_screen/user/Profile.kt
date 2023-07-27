package com.example.instacloneapp3.presentation.ui.screens.profile_screen.user

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.presentation.ui.core.components.HorizontalDraggableScreen
import com.example.instacloneapp3.presentation.ui.screens.content_list.PostsModal
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.content.UserContent
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.content.ContentScreens
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
UserProfile Screen
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfileScreen(
    navigationViewModel: NavigationViewModel,
    dragEnabled: Boolean = false,
    screenStackIndex: Int,
) {

    val scrollState = rememberScrollState()
    val postsGridState = rememberLazyGridState()
    val reelsGridState = rememberLazyGridState()
    val taggedGridState = rememberLazyGridState()
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
        dragEnabled = dragEnabled,
        screenStackIndex = screenStackIndex,
        navigationViewModel = navigationViewModel
    ){
        Column {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .verticalScroll(
                            state = scrollState,
                            enabled = scrollEnabled.value
                        )
                ) {

                    //--User Profile Header--
                    ProfileHeader( navigationViewModel = navigationViewModel )

                    //--User Profile Info section--
                    ProfileInfoTab( navigationViewModel = navigationViewModel )

                    //--User Content Section--
                    UserContent(
                        showModal = showModal,
                        scrollState = scrollState,
                        postsGridState = postsGridState,
                        reelsGridState = reelsGridState,
                        currentContent = currentContent,
                        taggedGridState = taggedGridState,
                        modalStartScrollIndex = modalStartScrollIndex,
                        transformOriginOffset = transformOriginOffset,
                    )
                }


                //--Modal Shadow--
                Canvas(
                    modifier = Modifier.fillMaxSize(),
                    onDraw = {
                        val size = size
                        drawRect(
                            size = size,
                            color = Color(
                                0F,
                                0F,
                                0F,
                                if (showModal.value) 0.3F else 0.0F
                            ),
                        )
                    }
                )


                //--Content List --
                androidx.compose.animation.AnimatedVisibility(
                    visible = showModal.value,
                    enter = scaleIn(
                        transformOrigin =
                        TransformOrigin(
                            720.5f,
                            1507.5f
                        ),
                    ),
                    exit = scaleOut(
                        transformOrigin =
                        TransformOrigin(
                            720.5f,
                            1507.5f
                        ),
                        animationSpec = tween(durationMillis = 6000, easing = EaseIn)
                    ),

                    ) {
                    PostsModal(
                        showModal = showModal,
                        navigationViewModel = navigationViewModel,
                        modalStartScrollIndex = modalStartScrollIndex,
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    InstaCloneApp3Theme {
        ProfileScreen(
            screenStackIndex = 0,
            navigationViewModel = hiltViewModel(),
        )
    }
}