package com.example.instacloneapp3.presentation.ui.screens.favourites_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import androidx.compose.runtime.Composable as Composable

@Composable
fun AddFavouritesScreen(){

    val posts = PostsRepo().getPosts()
    val suggestions = posts + posts

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AddFavouritesScreenHeader()
            Divider()
            HowItWorks()
            AddFavouritesSearchBar()
            LazyColumn(){
                item{
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    ){
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text ="Favourites",
                                fontWeight = FontWeight(600)
                            )
                            Text(
                                text = "Remove All",
                                fontWeight = FontWeight(600),
                                color = Color(0.0f, 0.447f, 0.808f, 1.0f))
                        }
                        Text(
                            text = "To get started, you can confirm the suggested favourites" +
                                    " based on your activity on Instagram",
                            textAlign = TextAlign.Start,
                            color = Color.Gray,
                            lineHeight = 17.sp,
                            fontSize = 14.sp
                        )
                    }
                }

                items(suggestions){suggestion->
                    SuggestionItem(suggestion)
                }
            }
        }

        Divider()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .align(Alignment.BottomCenter)
        ){
            Row(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0.0f, 0.51f, 0.914f, 1.0f))
                .padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Confirm Favourites",
                    color = Color.White
                )
            }
        }
    }


}

@Composable
fun SuggestionItem(user: Posts) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
        ){

            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = user.profile_picture),
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
            )

            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = "${user.user_name}",
                    fontWeight = FontWeight(600)
                )
                Text(text = "${user.user_name}")
            }

        }
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0.859f, 0.859f, 0.859f, 1.0f))
                .padding(vertical = 8.dp, horizontal = 30.dp)
        ){ Text(
            text ="Remove",
            fontWeight = FontWeight(700)
        )}
    }
}

@Composable
fun AddFavouritesSearchBar() {
    var text_value = remember { mutableStateOf("") }
    BasicTextField(
        value = text_value.value,
        onValueChange = {text_value.value = it},
        decorationBox = {innerTextField ->
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.88f)
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
                if (text_value.value.isEmpty()) {
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
            }
        }
    )
}

@Composable
fun HowItWorks() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0.941f, 0.941f, 0.941f))
            .padding(10.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "New posts from your favourites would appear higher in feed." +
                    " Only you can see who you add or remove",
            textAlign = TextAlign.Center,
            lineHeight = 19.sp,
            fontSize = 15.sp
        )
        Text(
            text = "How it works.",
            color = Color(0.0f, 0.149f, 0.533f, 1.0f),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(700),
            fontSize = 15.sp
        )

    }
}

@Composable
fun AddFavouritesScreenHeader() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "",
            modifier = Modifier.size(30.dp)
        )

        Text(
            text = "Favourites",
            fontWeight = FontWeight.Bold
        )

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "",
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AddFavouritesScreenPreview(){
    InstaCloneApp3Theme {
        AddFavouritesScreen()
    }
}