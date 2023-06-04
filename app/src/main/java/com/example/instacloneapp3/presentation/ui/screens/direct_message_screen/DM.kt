package com.example.instacloneapp3.presentation.ui.screens.direct_message_screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.instacloneapp3.presentation.mock_data.MessagesRepo
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DirectMessageScreen(
    hideModal: () -> Unit
){
    //Receiver (Friend a user(Sender) is chatting with)
    val receiver = PostsRepo().getPosts()[0]

    //Messages sent
    val messages = MessagesRepo().getMessages()

    //Keyboard Controller
    val keyboardController = LocalSoftwareKeyboardController.current

    //Visibility of keyboard
    val keyboardVisibility = remember { mutableStateOf(false)}

    //Direct message Screen Root Element
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
    ){
        DmHeader(
            receiver.profile_picture,
            receiver.user_name,
            hideModal,
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()
            ,

        )

        Messages(
            messages = messages,
            modifier = Modifier
                .weight(1F),
            receiver = receiver
        )

        DmInput(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
            ,
            keyboardVisibility
        )

    }
}


@Composable
@Preview(showBackground = true)
fun directMessageScreenPreview(){
    InstaCloneApp3Theme {
        val appState_ = rememberAppState()
        DirectMessageScreen(appState_::hideModal)
    }
}