package com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user

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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme





@Composable
fun RelationshipSearchBar(
    input: MutableState<String>,
    placeholder: String,
    leadingIcon: ImageVector = Icons.Default.Search,
    width: Float = 1f
){
    BasicTextField(
        singleLine = true,
        value = input.value,
        onValueChange = {input.value = it},
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(width)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(240, 240, 240))
                    .padding(horizontal = 10.dp, vertical = 10.dp)

            ) {

                //Place Holder Text
                if (input.value.isEmpty()){
                    Text(
                        "$placeholder",
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


                //Leading Icon
                if (leadingIcon != null){
                    Icon(
                        tint = Color.Gray,
                        imageVector = leadingIcon ,
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }

            }
        }
    )
}





val categories = listOf(
    listOf("Least Interacted with ", "40"),
    listOf("Most Shown in feed ", "40"),
    listOf("Hashtags, creators and businesses ", "59"),
)

@Composable
fun CategoryItem(user1: Posts, user2: Posts, textInfo: List<String>){

    Row(
        modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 10.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.width(70.dp)
        ){
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = user1.profile_picture),
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
            )

            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = user2.profile_picture),
                contentDescription = "",
                modifier = Modifier
                    .offset(x = 15.dp, y = 15.dp)
                    .size(45.dp)
                    .clip(CircleShape)
                    .border(4.dp, Color.White, CircleShape)

            )
        }
        Column() {
            Text("${textInfo[0]}", fontWeight = FontWeight.Bold)
            Text(
                text ="${user1.user_name} and ${textInfo[1]} others",
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun FollowingCategories(followingList: List<Posts>, text: String = "Categories"){
    Column {
        Text(text = text, fontSize = 18.sp, modifier = Modifier.padding(start = 10.dp))
        for(i in 0..5 step 2){
            CategoryItem(followingList[i], followingList[i+1], categories[i/2])
        }

    }
}

@Composable
fun FilterFollowing(){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
    ) {
        Row(){
            Text("Sort by")
            Spacer(Modifier.width(2.dp))
            Text("Default", fontWeight = FontWeight.Bold)
        }
        Row(){
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "" )
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
        }
    }
}

@Composable
fun FollowingButton(
    text:String,
    width:Float = 0.95f,
    textColor: Color = Color.Black,
    buttonColor: Color = Color(240, 240, 240)
){
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(buttonColor),
        modifier = Modifier
            .fillMaxWidth(width)
            .padding(vertical = 20.dp),
        shape = RoundedCornerShape(20)

    ) {
        Text(
            "$text",
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }
}

@Composable
fun FollowingItem(following: Posts, userProfile: Boolean = true, isFollowing: Boolean = true){

    val text = if(isFollowing) "Following" else "Follow"
    val buttonColor = if(isFollowing) Color(240, 240, 240) else Color(3, 169, 244, 255)
    val textColor = if(isFollowing) Color.Black else Color.White
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(){
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = following.profile_picture),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = "${following.user_name}",
                    maxLines = 1,
                    fontWeight = FontWeight(600),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.3f)
                )
                Text(
                    text = "${following.user_name}",
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.3f)
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            FollowingButton(text = text, width = 0.6f, buttonColor = buttonColor, textColor = textColor)
            Spacer(modifier = Modifier.width(8.dp))
            if(!userProfile){
                if(!isFollowing){
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                }else {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "")
                }
            }

        }
    }
}



@Composable
fun FollowingScreen(
    backNavigation: (String, String) -> Unit,
    modifier: Modifier
){
    val followingList = PostsRepo().getPosts()
    val search = remember{ mutableStateOf("")}
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                RelationshipSearchBar(input = search , placeholder = "Search",  width = 0.9f)
                Divider(Modifier.padding(vertical = 10.dp))
                FollowingCategories(followingList = followingList)
                Divider(Modifier.padding(vertical = 10.dp))
                FilterFollowing()
            }

            items(followingList){following ->
                FollowingItem(following = following)
            }

        }

    }
}

@Composable
@Preview(showBackground = true)
fun FollowingScreenPreview(){
    val appstate = rememberAppState()
    InstaCloneApp3Theme {
        LazyRow(){
            item{
                FollowingScreen(appstate::backNavigation, Modifier.fillParentMaxSize())
            }
        }
    }
}