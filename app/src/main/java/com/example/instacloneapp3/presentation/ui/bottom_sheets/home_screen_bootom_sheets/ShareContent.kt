package com.example.instacloneapp3.presentation.ui.bottom_sheets.home_screen_bootom_sheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

sealed class ShareLocation(val location: String, val icon : ImageVector) {
    object AddToStory : ShareLocation("home", Icons.Outlined.AddCircle)
    object ShareTo : ShareLocation("home", Icons.Outlined.Share)
    object CopyLink : ShareLocation("home", Icons.Outlined.List)
    object Messages: ShareLocation("home", Icons.Outlined.Email)
    object Messenger: ShareLocation("home", Icons.Outlined.MailOutline)
    object WhatsApp : ShareLocation("home", Icons.Outlined.CheckCircle)
    object SnapChat : ShareLocation("home", Icons.Outlined.Build)
    object Facebook : ShareLocation("home", Icons.Outlined.Person)
    object Twitter : ShareLocation("home", Icons.Outlined.ThumbUp)
}

val shareLocation = listOf<ShareLocation>(
    ShareLocation.AddToStory,
    ShareLocation.ShareTo,
    ShareLocation.CopyLink,
    ShareLocation.Messages,
    ShareLocation.Messenger,
    ShareLocation.WhatsApp,
    ShareLocation.SnapChat,
    ShareLocation.Facebook,
    ShareLocation.Twitter
)

@Composable
fun ShareContent(){
    val list = PostsRepo().getPosts()
    val users = list + list
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ){ ShareContentSearchBar() }
            LazyColumn() {
                items(users){user ->
                    UserItem(user)
                }
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(Color.White)
        )
        {
            Divider(Modifier.padding(bottom = 8.dp))
            ShareToLocation(Modifier)
        }

    }

}

@Composable
fun UserItem(user: Posts) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(){
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = user.profile_picture),
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(10.dp))
            Column() {
                Text(text = "${user.user_name}")
                Text(
                    text = "${user.user_name}",
                    color = Color.Gray
                )
            }
        }
        Row(
            modifier = Modifier
                .size(20.dp)
                .border(1.dp, Color.Gray, CircleShape)
        ){}
    }
}

@Composable
fun ShareContentSearchBar(){
    var textValue = remember { mutableStateOf("") }
    BasicTextField(
        value = textValue.value,
        onValueChange = {textValue.value = it},
        decorationBox = {innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0.847f, 0.847f, 0.847f, 1.0f))
                    .padding(10.dp)
            ){
                //Leading Icons
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "",
                    tint = Color.Gray
                )

                //Place Holder Text
                if (textValue.value.isEmpty()) {
                    Text(
                        "Search",
                        color = Color.Gray,
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

                //Trailing Icons

                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )




            }
        }
    )
}

@Composable
fun ShareToLocation(modifier: Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 10.dp)

    ) {
        LazyRow(
        ){
            items(shareLocation){ location ->
                ShareToLocationItem(location)
            }
        }
    }

}

@Composable
fun ShareToLocationItem(location: ShareLocation) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        ){
            Icon(imageVector = location.icon, contentDescription = "")
        }
        Spacer(Modifier.width(5.dp))
        Text("${location.location}")
    }
}

@Composable
@Preview(showBackground = true)
fun ShareContentPreview(){
    InstaCloneApp3Theme {
        ShareContent()
    }
}