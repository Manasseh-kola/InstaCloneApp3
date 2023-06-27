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

@Composable
fun ProfileDropDown(
    username: String,
    showBottomSheet: MutableState<Boolean>,
    currentBottomSheet: MutableState<BottomSheets>
){
    Row{
        Text(username,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.size(5.dp))

        Icon(
            Icons.Filled.KeyboardArrowDown,
            "",
            Modifier.clickable{
                currentBottomSheet.value = BottomSheets.TRY_NEW_ACCOUNT
                showBottomSheet.value = true

            }
        )

    }
}

@Composable
fun ProfileHeader(
    showBottomSheet: MutableState<Boolean>,
    currentBottomSheet: MutableState<BottomSheets>
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        ProfileDropDown(user.user_name, showBottomSheet, currentBottomSheet)

        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_outlined_add),
                contentDescription = "",
                modifier = Modifier
                    .size(18.dp)
                    .clickable {
                        currentBottomSheet.value = BottomSheets.CREATE_NEW_CONTENT
                        showBottomSheet.value = true
                    }
            )
            Spacer(Modifier.width(20.dp))
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = "",
                modifier = Modifier
                    .clickable {
                        currentBottomSheet.value = BottomSheets.MANAGE_USER_ACCOUNT
                        showBottomSheet.value = true
                    }

            )
        }

    }
}

@Composable
fun ProfileInfo(
    amount:String,
    info:String,
    navigateToRoute: (String) -> Unit,
    destination:String,
    userIndex: Int = 0
){
    val currentPage = if(info == "Followers") "0" else "1"
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = {navigateToRoute("$destination/$currentPage*$userIndex")})
    ) {
        Text(amount)
        Text(info)
    }
}

@Composable
fun StoryHighlightItem(story: Stories) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(id = story.imageRes),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Text("Category")
    }

}

@Composable
fun StoryHighlightList(stories: List<Stories>) {

    LazyRow(
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item{

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box(

                ) {
                    Canvas(modifier = Modifier.size(50.dp),
                        onDraw = {
                            val canvasWidth = size.width
                            val canvasHeight = size.height

                            drawCircle(
                                color = Color.Gray,
                                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                                radius = size.minDimension / 1.9F,
                            )

                            drawCircle(
                                color = Color.LightGray,
                                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                                radius = size.minDimension / 2F,

                                )
                        })

                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(30.dp),
                        tint = Color.Gray,

                    )
                }
                Text("New")
            }


        }

        items(stories){story->
            StoryHighlightItem(story = story)
        }
    }
}

@Composable
fun StoryHighlightsDropDown(){

    val stories = StoriesRepo().getStories()
    var expand = remember { mutableStateOf(false) }
    val icon = if (expand.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = { expand.value = !expand.value }
                ),
            horizontalArrangement = Arrangement.SpaceBetween,


            ) {
            Text(
                "Story Highlights",
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.size(5.dp))

            Icon(
                icon,
                ""
            )

        }
        if (expand.value){
            StoryHighlightList(stories)
        }else{
            Divider()
        }
    }
}


@Composable
fun ProfileInfoTab(navigateToRoute: (String) -> Unit){

    val stories = StoriesRepo().getStories()
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = user.profile_picture),
                        contentDescription = "",
                        modifier = storyImageModifier
                    )
                    Icon(
                        imageVector = Icons.Outlined.AddCircle,
                        contentDescription = "",
                        tint = Color(3, 109, 202, 201),
                        modifier = Modifier
                            .offset(y = 3.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(1.dp)
                            .size(25.dp)
                            .align(Alignment.BottomEnd)

                    )
                }

            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .width(250.dp)
                    .offset(y = 10.dp)
                    .padding(5.dp)
            ) {

                ProfileInfo("7", "Posts",navigateToRoute,"")
                ProfileInfo(user.followers_count.toString(), "Followers",navigateToRoute,"relationship")
                ProfileInfo(user.following_count.toString(), "Following",navigateToRoute,"relationship")
            }

        }
        Text(user.user_name,Modifier.padding(horizontal = 5.dp))
        Text(user.bio, modifier = Modifier.padding(horizontal = 5.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 3.dp)
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(3, 109, 202, 201)),
                modifier = Modifier
                    .weight(1F)
                    .padding(end = 2.dp),
                shape = RoundedCornerShape(25)

            ) {
                Text("Edit Profile", color = Color.White)
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(3, 109, 202, 201)),
                modifier = Modifier
                    .weight(1F)
                    .padding(start = 2.dp),
                shape = RoundedCornerShape(25)

            ) {
                Text("Share Profile", color = Color.White)
            }
        }

        StoryHighlightsDropDown()

    }
}

@Composable
fun PostItem(
    image: Int,
    index: Int,
    showModal: MutableState<Boolean>,
    modalStartScrollIndex: MutableState<Int>,
    transformOriginOffset: MutableState<Offset>
){
    Image(
        contentScale = ContentScale.Crop,
        painter = painterResource(id = image),
        contentDescription = "",
        modifier = Modifier
            .size(150.dp)
            .clickable {
                modalStartScrollIndex.value = index
                showModal.value = true
            }
    )
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfileScreen(
    navigateToRoute: (String) -> Unit,
    showBottomSheet: MutableState<Boolean>,
    currentBottomSheet: MutableState<BottomSheets>,
    currentModalSheet: MutableState<ModalSheets>,) {


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
            ProfileHeader(showBottomSheet, currentBottomSheet)
            ProfileInfoTab(navigateToRoute = navigateToRoute)
            UserContent(
                scrollState,
                postsGridState,
                reelsGridState,
                taggedGridState,
                currentContent,
                showModal,
                modalStartScrollIndex,
                transformOriginOffset
            )
        }


        //Modal Shadow
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                val size = size
                drawRect(
                    color = Color.hsv(
                        0F,
                        0F,
                        0F,
                        if(showModal.value) 0.3F else 0.0F),
                    size = size
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
                showModal,
                modalStartScrollIndex,
                currentModalSheet,
                currentBottomSheet,
                showBottomSheet
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    val appstate = rememberAppState()
    InstaCloneApp3Theme() {
        ProfileScreen(
            appstate::onNavigateToScreen,
            remember { mutableStateOf(false) },
            remember{ mutableStateOf(BottomSheets.NO_SHEET)},
            remember{ mutableStateOf(ModalSheets.NO_SHEET)}
        )
    }
}