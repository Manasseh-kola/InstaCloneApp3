package com.example.instacloneapp3.presentation.ui.screens

import android.app.Notification
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.screens.home_screen.HomeModals
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

@Composable
fun NotificationsScreen(closeModal: () -> Unit){

    val list = PostsRepo().getPosts()
    val activities = list + list
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        NotificationsScreenHeader(){
            closeModal()
        }
        LazyColumn(
        ){
            item{
                AdsActivity()
            }

            items(activities){activity->
                NotificationItem(activity)
            }
        }
    }
}

@Composable
fun NotificationItem(
    activity: Posts,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ){
        Row(){
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = activity.profile_picture),
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(10.dp))
            Text(
                buildAnnotatedString{
                    append("${activity.user_name} who you might know" +
                                " is on instagram.")
                    withStyle(style = SpanStyle(color = Color.Gray)){
                        append("${(1..5).random()}d")
                    }

                },
                modifier = Modifier.fillMaxWidth(0.6f)
            )
        }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0.129f, 0.588f, 0.953f, 1.0f))
                .padding(horizontal = 20.dp, vertical = 8.dp),

        ){
            Text(
                text = "Follow",
                color = Color.White,
                fontWeight = FontWeight(500)
            )
        }
    }
}

@Composable
fun AdsActivity() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()

    ) {
        Row() {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(50.dp)
            )
            Column() {
                Text(
                    text = "Ads",
                    fontWeight = FontWeight(500)
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Recent Activity from you ads.",
                    color = Color.Gray
                )

            }
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.Gray,
            modifier = Modifier
                .size(30.dp)
        )
    }
    Divider(Modifier.padding(bottom = 10.dp))
    Text(
        text = "This week",
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp,
        modifier = Modifier.padding(start = 10.dp)
    )
}

@Composable
fun NotificationsScreenHeader(closeModal: ()->Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(40.dp)
                .clickable {
                    closeModal()
                }
        )
        Text(
            text = "Notifications",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold

        )
    }
}


@Composable
@Preview(showBackground = true)
fun NotificationsScreenPreview(){
    InstaCloneApp3Theme {
        NotificationsScreen(){}
    }
}