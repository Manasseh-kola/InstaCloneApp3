package com.example.instacloneapp3.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


@Composable
fun MessagesHeader(username: String, modifier: Modifier.Companion, backNavigation: (String, String) -> Unit) {
    Row(
        modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(10.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(){
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .clickable(onClick = {backNavigation("home", "home")})
            )
            Text("$username")
            Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "")
        }
        Row(){
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "")
            Icon(imageVector = Icons.Filled.Create, contentDescription = "")
        }
    }
}


@Composable
fun SearchBar(){
    var text_value = remember { mutableStateOf("") }

    Row(
        verticalAlignment =  Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {

        BasicTextField(
            value = text_value.value,
            onValueChange = {text_value.value = it},
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
                    if (text_value.value.isEmpty()) {
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
        ,
        horizontalArrangement = Arrangement.SpaceEvenly
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
    senderProfilePicture : Int,
    senderName: String,
    message: String,
    id:Int,
    navigateToRoute: (String) -> Unit
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 10.dp)
            .clickable(onClick = { navigateToRoute("directMessage") })
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
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
                Text("$senderName")
                Text("$message")
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

@Composable
fun MessagesScreen(
    backNavigation: (String, String) -> Unit,
    navigateToRoute: (String) -> Unit
) {

    val messages = PostsRepo().getPosts()
    Box(){

       LazyColumn(
           modifier = Modifier
               .fillMaxSize()
       ){

           item(){
               Spacer(
                   Modifier
                       .fillMaxWidth()
                       .height(40.dp))
           }

           item {
               SearchBar()
           }
           item {
               MessageCategoryBar()
           }


           items(messages){ message ->
               MessageItem(
                   message.profile_picture,
                   message.user_name,
                   message.caption,
                   message.id,
                   navigateToRoute
               )
           }

       }
        MessagesHeader(user.user_name, modifier = Modifier, backNavigation)
    }
}

@Composable
fun dummy(a:String,b:String){}

@Composable
@Preview(showBackground = true)
fun MessagesPreviewScreen(){
    val appState = rememberAppState()
    InstaCloneApp3Theme() {
        MessagesScreen(
            appState::backNavigation,
            appState::onNavigateToScreen
        )
    }
}
