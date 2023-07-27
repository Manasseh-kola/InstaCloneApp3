package com.example.instacloneapp3.presentation.ui.screens.direct_message_screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.presentation.mock_data.MessagesRepo
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.components.HorizontalDraggableScreen
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DirectMessageScreen(navigationViewModel: NavigationViewModel, screenStackIndex: Int){
    //Receiver (Friend a user(Sender) is chatting with)
    val receiver = PostsRepo().getPosts()[0]

    //Messages sent
    val messages = MessagesRepo().getMessages()

    //Keyboard Controller
    val keyboardController = LocalSoftwareKeyboardController.current

    //Visibility of keyboard
    val keyboardVisibility = remember { mutableStateOf(false)}



    //Direct message Screen Root Element
    HorizontalDraggableScreen(
        screenStackIndex = screenStackIndex,
        navigationViewModel = navigationViewModel
    ) {
        Surface(shadowElevation = 10.dp){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .clickable(onClick = {
                        if (keyboardVisibility.value) {
                            keyboardController?.hide()
                            keyboardVisibility.value = false
                        }
                    })
                    .onGloballyPositioned { layoutCoordinates ->
                        val height = layoutCoordinates.size.height
                        if (height == 1275) {
                            if (!keyboardVisibility.value) {
                                keyboardVisibility.value = true
                            }
                        } else if (keyboardVisibility.value) {
                            keyboardVisibility.value = false
                        }
                    }
            ) {
                DmHeader(
                    navigationViewModel = navigationViewModel,
                    profilePicture = receiver.profile_picture,
                    userName = receiver.user_name,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                        .fillMaxWidth(),

                    )

                Messages(
                    messages = messages,
                    modifier = Modifier
                        .weight(1F),
                    receiver = receiver
                )

                DmInput(
                    keyboardVisibility = keyboardVisibility,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth(),

                    )

            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun DirectMessageScreenPreview(){
    InstaCloneApp3Theme {
        DirectMessageScreen(navigationViewModel = hiltViewModel(), screenStackIndex = 0)
    }
}