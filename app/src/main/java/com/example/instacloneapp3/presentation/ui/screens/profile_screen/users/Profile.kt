package com.example.instacloneapp3.presentation.ui.screens.profile_screen.users

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.mock_data.StoriesRepo
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.screens.home_screen.LikedBy
import com.example.instacloneapp3.presentation.ui.screens.home_screen.SuggestedForYou
import com.example.instacloneapp3.presentation.ui.screens.home_screen.storyImageModifier
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.content.UserContent
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.user.ProfileInfo
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.user.StoryHighlightItem
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel


@Composable
fun UsersProfileScreen(
    navigateToRoute: (String) -> Unit,
    showHomeModal: MutableState<Boolean>,
    navigationViewModel: NavigationViewModel
){
    val navigationUiState = navigationViewModel.navigationState.collectAsState()
    val currentUser = navigationUiState.value.currentUser!!
    val userIndex = PostsRepo().getPosts().indexOfFirst{ it.user_name == currentUser.user_name}


    val configuration = LocalConfiguration.current
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

    val suggestionDropDown = remember{ mutableStateOf(false)}
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

    Log.i("scrollState", "${scrollState.value}  ${scrollState.maxValue}")

    Box(
        modifier = Modifier.background(Color.White)
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    state = scrollState,
                    enabled = scrollEnabled.value
                )
        ){

            ProfileHeader(
                navigationViewModel = navigationViewModel,
                userName = currentUser.user_name
            ){showHomeModal.value = false}

            ProfileInfoTab(
                userIndex = userIndex,
                profileOwner = currentUser,
                navigateToRoute = navigateToRoute,
                suggestionDropDown = suggestionDropDown,

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

@Composable
fun StoryHighlights() {
    val stories = StoriesRepo().getStories()
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(),
    ){
        items(stories){story->
            StoryHighlightItem(story = story)
        }
    }
}



@Composable
fun ProfileHeader(
    navigationViewModel: NavigationViewModel,
    userName: String,
    hideModal: ()-> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.CenterStart)
                .clickable {
                    hideModal()
                }
        )

        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            text = userName,
            modifier = Modifier
                .align(Alignment.Center)
        )

        Row(
            modifier = Modifier.align(Alignment.CenterEnd)
        ){

            //Notifications Header Button
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navigationViewModel.openBottomSheet(
                            currentBottomSheet = BottomSheets.USERS_PROFILE_NOTIFICATIONS,
                            currentScreen = AppScreenTypes.UsersProfile
                        )
                    }
            )


            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}


@Composable
fun ProfileInfoTab(
    profileOwner: Posts,
    navigateToRoute: (String) -> Unit,
    suggestionDropDown: MutableState<Boolean>,
    userIndex: Int,

    ){
    val followers = PostsRepo().getPosts().takeLast(2)
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth()
        ) {

            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = profileOwner.profile_picture),
                contentDescription = "",
                modifier = storyImageModifier
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .width(250.dp)
                    .offset(y = 10.dp)
                    .padding(5.dp)
            ) {

                ProfileInfo("7", "Posts",navigateToRoute,"")
                ProfileInfo(user.followers_count.toString(), "Followers",navigateToRoute,"usersRelationship", userIndex)
                ProfileInfo(user.following_count.toString(), "Following",navigateToRoute,"usersRelationship", userIndex)
            }

        }

        Text(
            text = profileOwner.user_name,
            fontWeight = FontWeight(700)
        )

        Text(profileOwner.caption)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 20.dp)
        ) {
            LikedBy(
                imageSize = 35.dp,
                borderWidth = 4.dp,
                imageSpacing = 50.dp,
            )

            Spacer(Modifier.width(10.dp))

            Column {
                Text(
                    text = buildAnnotatedString {
                        append("Followed by ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight(800))){
                            append("${followers[0].user_name}, ")
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight(800))){
                            append(followers[1].user_name)
                        }
                     },
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Text(
                    buildAnnotatedString {
                        append("and ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight(800))){
                            append("${user.followers_count} others")
                        }
                    }
                )

            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {

            //Follow/Unfollow Toggle Button
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                modifier = Modifier
                    .weight(1F)
                    .padding(end = 2.dp),
                shape = RoundedCornerShape(25)

            ) {
                Text("Following", color = Color.Black)
                Spacer(Modifier.width(2.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                    tint = Color.Black
                )
            }

            //Message Button
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                modifier = Modifier
                    .weight(1F)
                    .padding(horizontal = 2.dp),
                shape = RoundedCornerShape(25)

            ) {
                Text("Message", color = Color.Black)
            }

            //Suggested Profiles to Follow
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                modifier = Modifier
                    .padding(start = 2.dp),
                shape = RoundedCornerShape(25)

            ) {
                Icon(
                    imageVector = Icons.Default.Person, contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable {
                            suggestionDropDown.value = !suggestionDropDown.value
                        }
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun UsersProfileScreenPreview(){
    val appState = rememberAppState()
    InstaCloneApp3Theme {
        UsersProfileScreen(
            navigationViewModel = hiltViewModel(),
            navigateToRoute = appState::onNavigateToScreen,
            showHomeModal = remember{ mutableStateOf(false)},
        )
    }
}
