package com.example.instacloneapp3.presentation.ui.navigation.destinations.non_root_destinations.messages_destination

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.screens.direct_message_screen.DirectMessageScreen
import com.example.instacloneapp3.presentation.ui.screens.messages_screen.MessagesScreen
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel
import kotlinx.coroutines.launch

/*
Messages Screen Entry-point/Root
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessagesDestination(
    navigationViewModel: NavigationViewModel,
    pagerState: PagerState
) {
    val navigationUiState = navigationViewModel.navigationState.collectAsState()
    val messagesStack = navigationUiState.value.messagesStack
    val coroutineScope = rememberCoroutineScope()

    fun scrollToPage(page: Int){
        coroutineScope.launch {
            pagerState.animateScrollToPage(page)
        }
    }

    Box {
        for ((i, screen) in messagesStack.withIndex()) {
            when (screen) {

                //--Direct Messages Screen--
                is AppScreenTypes.DirectMessages -> {
                    AnimatedVisibility(visible = i >= messagesStack.size - 2) {
                        DirectMessageScreen(
                            screenStackIndex = i,
                            navigationViewModel = navigationViewModel
                        )
                    }
                }

                //--Messages Screen--
                else -> {
                    AnimatedVisibility(visible = i >= messagesStack.size - 2) {
                        MessagesScreen(
                            screenStackIndex = i,
                            navigationViewModel = navigationViewModel
                        ){
                            scrollToPage(0)
                        }
                    }
                }
            }
        }
    }
}