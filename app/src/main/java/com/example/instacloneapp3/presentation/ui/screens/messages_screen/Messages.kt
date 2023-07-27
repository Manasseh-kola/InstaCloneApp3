package com.example.instacloneapp3.presentation.ui.screens.messages_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.core.components.HorizontalDraggableScreen
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel


/*
Messages Screen
 */

@Composable
fun MessagesHeader(
    username: String,
    modifier: Modifier,
    onClick: ()->Unit,
) {
    Row(
        modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(10.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .clickable{ onClick() }
            )
            Text(username)
            Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "")
        }
        Row {
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "")
            Icon(imageVector = Icons.Filled.Create, contentDescription = "")
        }
    }
}


@Composable
fun SearchBar(){
    val textValue = remember { mutableStateOf("") }

    Row(
        verticalAlignment =  Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {

        BasicTextField(
            value = textValue.value,
            onValueChange = {textValue.value = it},
            decorationBox = {innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(0.88f)
                        .border(
                            shape = RoundedCornerShape(15.dp),
                            color = Color.Black,
                            width = 1.dp
                        )
                        .padding(10.dp)
                ){
                    //Leading Icons
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "",
                    )

                    //Place Holder Text
                    if (textValue.value.isEmpty()) {
                        Text(
                            "Search",
                            color = Color.Black,
                            modifier = Modifier
                                .padding(start = 30.dp)
                        )
                    }

                    //Text Field
                    Row(
                        modifier = Modifier
                            .padding(start = 30.dp)
                            .fillMaxWidth()

                    ){
                        innerTextField()
                    }
                }
            }
        )

        Text("Filter", color = Color.Blue)
    }
}

@Composable
fun MessageCategoryBar(){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
        ,

    ){
        Text("Primary",
            modifier = Modifier
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(10.dp),
                )
                .padding(horizontal = 25.dp, vertical = 8.dp)

        )
        Text("General",
            modifier = Modifier
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(10.dp),
                )
                .padding(horizontal = 25.dp, vertical = 8.dp)

        )
        Text("requests",
            modifier = Modifier
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(10.dp),
                )
                .padding(horizontal = 25.dp, vertical = 8.dp)

        )
    }
}

@Composable
fun MessageItem(
    navigationViewModel: NavigationViewModel,
    senderProfilePicture : Int,
    senderName: String,
    message: String,
) {
    //--Navigation State
    val navigationUiState = navigationViewModel.navigationState.collectAsState()

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 10.dp)
            .clickable {
                //Navigate to dm Screen
                navigationViewModel.pushToBackStack(
                    AppScreenTypes.DirectMessages(
                        screenIndex = navigationUiState.value.currentStack.size
                    )
                )
            }
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = senderProfilePicture),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)


            )
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(senderName)
                Text(message)
            }

        }
        Icon(
            painter = painterResource(id = R.drawable.ic_outlined_camera),
            contentDescription = "",
            modifier = Modifier
                .size(22.dp)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MessagesScreen(
    navigationViewModel: NavigationViewModel,
    screenStackIndex: Int,
    onClick: () -> Unit
) {

    //Keyboard Controller
    val keyboardController = LocalSoftwareKeyboardController.current

    //Visibility of keyboard
    val keyboardVisibility = remember { mutableStateOf(false)}
    val messages = PostsRepo().getPosts()


    HorizontalDraggableScreen(
        dragEnabled = false,
        screenStackIndex = screenStackIndex,
        navigationViewModel = navigationViewModel
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = {
                        if (keyboardVisibility.value) {
                            keyboardController?.hide()
                            keyboardVisibility.value = false
                        }
                    }
                )
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
            Box {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    item {
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                        )
                    }

                    item {
                        SearchBar()
                    }
                    item {
                        MessageCategoryBar()
                    }


                    items(messages) { message ->
                        MessageItem(
                            senderProfilePicture = message.profile_picture,
                            navigationViewModel = navigationViewModel,
                            senderName = message.user_name,
                            message = message.caption,
                        )
                    }

                }
                MessagesHeader(
                    username = user.user_name,
                    modifier = Modifier,
                ) {
                    onClick()
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun MessagesPreviewScreen(){
    InstaCloneApp3Theme {
        MessagesScreen(
            navigationViewModel = hiltViewModel(),
            screenStackIndex = 0
        ){}
    }
}
