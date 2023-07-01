package com.example.instacloneapp3.presentation.ui.screens.profile_screen.user

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.mock_data.Stories
import com.example.instacloneapp3.presentation.mock_data.StoriesRepo
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.modals.profile_screen_modals.PostsModal
import com.example.instacloneapp3.presentation.ui.screens.home_screen.storyImageModifier
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.content.UserContent
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfileScreen(
    navigateToRoute: (String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
    navigationViewModel: NavigationViewModel,
) {


    val scrollState = rememberScrollState()
    val postsGridState = rememberLazyGridState()
    val reelsGridState = rememberLazyGridState()
    val taggedGridState = rememberLazyGridState()

    val postScrollDisabled = remember {
        derivedStateOf {
            postsGridState.firstVisibleItemScrollOffset > 0
        }
    }
    val reelsScrollDisabled = remember {
        derivedStateOf {
            reelsGridState.firstVisibleItemScrollOffset > 0
        }
    }
    val taggedScrollDisabled = remember {
        derivedStateOf {
            taggedGridState.firstVisibleItemScrollOffset > 0
        }
    }

    val modalStartScrollIndex = remember{ mutableStateOf(0)}
    val showModal = remember{ mutableStateOf(false)}
    val scrollEnabled = remember { mutableStateOf(true)}
    val currentContent = remember{ mutableStateOf("postsContent")}
    val transformOriginOffset = remember{ mutableStateOf(Offset(0f,0f))}

    

    when(currentContent.value){
        "postsContent" -> {
            scrollEnabled.value = !postScrollDisabled.value
        }
        "reelsContent" -> {
            scrollEnabled.value = !reelsScrollDisabled.value
        }
        "taggedContent" -> {
            scrollEnabled.value = !taggedScrollDisabled.value
        }
    }


    Box(){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    state = scrollState,
                    enabled = scrollEnabled.value
                )
        ) {

            //User Profile Header
            ProfileHeader(navigationViewModel = navigationViewModel)

            //User Profile Info section
            ProfileInfoTab(navigateToRoute = navigateToRoute)

            //UserContentSection
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


        //Modal Shadow
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
                        if(showModal.value) 0.3F else 0.0F
                    ),
                )
            }
        )


        //Modal
        AnimatedVisibility(
            visible = showModal.value,
            enter = scaleIn(
                transformOrigin =
                TransformOrigin(
                    transformOriginOffset.value.x,
                    transformOriginOffset.value.y
                ),
            ),
            exit = scaleOut(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessHigh
                )
            ),

        ) {
            PostsModal(
                showModal = showModal,
                currentModalSheet = currentModalSheet,
                navigationViewModel = navigationViewModel,
                modalStartScrollIndex = modalStartScrollIndex,

            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    val appState = rememberAppState()
    InstaCloneApp3Theme() {
        ProfileScreen(
            navigationViewModel = hiltViewModel(),
            navigateToRoute = appState::onNavigateToScreen,
            currentModalSheet = remember{ mutableStateOf(ModalSheets.NO_SHEET)}
        )
    }
}