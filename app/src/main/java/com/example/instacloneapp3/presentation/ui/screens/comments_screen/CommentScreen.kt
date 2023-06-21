package com.example.instacloneapp3.presentation.ui.screens.comments_screen

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.screens.story_screen.emojis
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import kotlin.math.roundToInt

@Composable
fun CommentScreen(showModal: MutableState<Boolean>, hideModal: () -> Unit){

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val list = PostsRepo().getPosts()
    val comments = list + list
    var offsetX = remember { mutableStateOf(0f) }
    var animatedOffsetX = animateFloatAsState(offsetX.value)
    //Screen is dragged
    val dragged = remember{ mutableStateOf(false)}


    Log.i("offset", "${offsetX.value}")
    Log.i("offset", "${animatedOffsetX.value}anim")
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .offset { IntOffset(animatedOffsetX.value.roundToInt(), 0) }
                .fillMaxSize()
                .background(Color.White)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            val xBreakPoint = 750.4563f

                            if (offsetX.value >= xBreakPoint) {
                                hideModal()
                            } else if (offsetX.value != 0.0f) {
                                offsetX.value = 0.0f
                            }
                        },
                    ) { change, dragAmount ->
                        change.consume()
                        if (dragAmount.x + offsetX.value > 0.0f) {
                            offsetX.value += dragAmount.x
                        }
                    }
                }
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                CommentScreenHeader(showModal)
                Divider()
            }
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                items(comments) { comment ->
                    CommentItem(comment)
                }

            }

        }

        CommentScreenFooter(
            modifier = Modifier
                .align(Alignment.BottomCenter)

        )
    }

}

@Composable
fun CommentItem(comment: Posts) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ){
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(id = comment.profile_picture),
            contentDescription = "",
            modifier = Modifier
                .padding(end = 15.dp)
                .size(30.dp)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)

        ){
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("${comment.user_name}")
                    }

                    withStyle(style = SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
                        append(" ${(1..60).random()}m")
                    }
                }

            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .fillMaxWidth()
            ){
                Text(text = "${comment.caption}")
                Icon(
                    painter = painterResource(id = R.drawable.ic_outlined_favorite ),
                    contentDescription = "",
                    modifier = Modifier
                        .size(14.dp)
                )
            }

            Row(){
                Text(
                    text = "${(1..99).random()} likes",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight(600)
                )
                Text(
                    text = "Reply",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight(600),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable {}
                )
            }

            if(comment.user_name.startsWith("A")){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        Modifier
                            .padding(top = 10.dp, end = 10.dp)
                            .height(1.dp)
                            .width(30.dp)
                            .background(Color.Gray)
                    ){}
                    Text(
                        text = "View ${(1..9).random()} more replies",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                }
            }


        }
    }
}


@Composable
fun CommentScreenFooter(modifier: Modifier) {
    Column(
        modifier = modifier
            .background(color = Color.White)
    ) {
        Divider(Modifier.padding(bottom = 8.dp))
        CommentQuickReply()
        CommentReply()
    }
}

@Composable
fun CommentReply() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ){
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(user.profile_picture),
            contentDescription = "",
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
        )
        CommentInput(modifier = Modifier.fillMaxWidth(0.99f))

    }
}

@Composable
fun CommentQuickReply() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 10.dp)
    ){
        for(emoji in emojis.take(5)){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(60.dp)
                    .height(45.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(240, 240, 240))

            ){
                Text(
                    text = "$emoji",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}


@Composable
fun CommentScreenHeader(showModal: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ){
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(40.dp)
                .clickable {
                    showModal.value = false
                }
        )
        Text(
            text = "Comments",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CommentScreenPreview(){
    val appState = rememberAppState()
    InstaCloneApp3Theme {
        CommentScreen(remember{mutableStateOf(false)}, appState::hideModal)
    }

}