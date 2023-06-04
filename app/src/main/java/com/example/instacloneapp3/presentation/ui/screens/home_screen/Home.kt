package com.example.instacloneapp3.presentation.ui.screens.home_screen


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.mock_data.User
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import kotlinx.coroutines.launch


val storyImageModifier = Modifier
    .size(90.dp)
    .clip(CircleShape)


val colorStops = arrayOf(
    0.0f to Color(0xFFd71069),
    0.5f to Color(0xFFe25d6a),
    1f to Color(0xFFe9ad55)
)

@Composable
fun StoryItem(
    story: Posts,
    navigateToRoute: (String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable(onClick = { navigateToRoute("story") })
        ) {

            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = story.imageRes),
                contentDescription = "",
                modifier = storyImageModifier
                    .border(5.dp, Color.White, CircleShape)
            )

            Canvas(modifier = Modifier.size(90.dp),

                onDraw = {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    drawCircle(
                        brush = Brush.linearGradient(
                            colorStops = colorStops,
                            start = Offset(100f, 0.0f),
                            end = Offset(0.0f, 100.0f)
                        ),
                        center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                        radius = size.minDimension / 2,
                        style = Stroke(8.6F),

                        )
                })
        }

        Text(
            "${story.user_name}",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(100.dp)
        )
    }

}

@Composable
fun StoriesList(
    user: User,
    stories: List<Posts>,
    modifier: Modifier,
    navigateToRoute: (String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
    drawerState: DrawerState,
) {

    val modalScope = rememberCoroutineScope()

    LazyRow(
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item{

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = user.profile_picture),
                        contentDescription = "",
                        modifier = storyImageModifier
                            .clickable {
                                currentModalSheet.value = ModalSheets.STORY_MODAL
                                modalScope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }


                    )
                    Icon(
                        imageVector = Icons.Outlined.AddCircle,
                        contentDescription = "",
                        tint = Color(3, 140, 202, 255),
                        modifier = Modifier
                            .offset(y = 8.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(2.dp)
                            .size(30.dp)
                            .align(Alignment.BottomEnd)

                    )
                }
                Text(
                    "Your Story",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )
            }


        }
        items(stories){story->
            StoryItem(
                story = story,
                navigateToRoute
            )
        }
    }
}

@Composable
fun PostHeader(profile_picture: Int, user_name: String, modifier: Modifier){

    Row(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = profile_picture),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(10.dp))
            Text(user_name)
        }

        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "",

            )


    }

}

@Composable
fun PostFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            modifier = Modifier
                .width(100.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_outlined_favorite),
                contentDescription ="",
                modifier = Modifier
                    .size(25.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_outlined_comment),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_dm),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.bookmark_outline),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
        )
    }

}

@Composable
fun UserCaption(username: String, caption: String){

    Row(
    ) {
        Text("$username ", fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(5.dp))
        Text(caption)
    }
}

@Composable
fun UserComment(){
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
    ){
        Text("View all comments", fontSize = 12.sp, color = Color.Gray)
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = user.profile_picture),
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(20.dp)
            )
            Spacer(Modifier.width(2.dp))
            Text("   Add comment...", fontSize = 10.sp, color = Color.Gray)

        }
    }
}

@Composable
fun PostItem(
    post: Posts,
    showModal: MutableState<Boolean> = remember{ mutableStateOf(false)}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ){

        PostHeader(
            post.profile_picture,
            post.user_name,
            modifier = Modifier.fillMaxWidth())
        Box(){
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = post.imageRes),
                contentDescription = "",
                modifier = Modifier
                    .size(450.dp)

            )
        }
        Column(modifier = Modifier.padding(10.dp)){
            PostFooter()
            UserCaption(username = post.user_name, caption = post.caption)
            UserComment()
        }
        Divider()


    }
}

@Composable
fun PostsList(
    posts: List<Posts>,
    modifier: Modifier,
    navigateToRoute: (String) -> Unit,
    backNavigation: (String, String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
    drawerState: DrawerState
) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),

        ){
        item{
            TopBar(navigateToRoute, backNavigation)
            StoriesList(
                User(),
                posts,
                modifier = Modifier.fillMaxWidth(),
                navigateToRoute,
                currentModalSheet,
                drawerState

            )
            Divider()
        }
        items(posts){post ->
            PostItem(post = post)
        }

    }
}

val user = User()
@Composable
fun HomeScreen(
    navigateToRoute: (String) -> Unit,
    backNavigation: (String, String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
    drawerState: DrawerState,


    ) {
//    val viewModel: UserViewModel = viewModel()
    val posts = PostsRepo()
    val stories_repo = PostsRepo()
    Column(modifier = Modifier.fillMaxSize()){
        PostsList(
            posts.getPosts(),
            modifier = Modifier.fillMaxWidth(),
            navigateToRoute = navigateToRoute,
            backNavigation = backNavigation,
            currentModalSheet,
            drawerState
        )
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreen1Preview() {
    val appstate = rememberAppState()
    InstaCloneApp3Theme() {
        HomeScreen(
            appstate::onNavigateToScreen,
            appstate::backNavigation,
            remember{ mutableStateOf(ModalSheets.NO_SHEET) },
            rememberDrawerState(initialValue = DrawerValue.Closed)
        )
    }
}



