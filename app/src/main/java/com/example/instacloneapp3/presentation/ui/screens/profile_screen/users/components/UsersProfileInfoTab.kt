package com.example.instacloneapp3.presentation.ui.screens.profile_screen.users.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.screens.home_screen.components.LikedBy
import com.example.instacloneapp3.presentation.ui.screens.home_screen.storyImageModifier
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

@Composable
fun ProfileInfo(
    info: String,
    userIndex: Int,
    amount: String,
    currentPage: String?,
    navigationViewModel: NavigationViewModel,
){
    //Current page in Relationship screens

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .clickable {
                /*
                Navigate to relationship screens
                 */
                if(currentPage != null) {
                    navigationViewModel.pushToBackStack(
                        AppScreenTypes.UsersRelationShip(args = currentPage, userIndex = userIndex)
                    )
                }
            }
    ) {
        //Number of Relationship/PostsInfo category
        Text(
            text = amount,
            fontWeight = FontWeight.Bold
        )

        //Type of Relationship/PostsInfo
        Text(text = info)
    }
}


@Composable
fun ProfileInfoTab(
    profileOwner: Posts,
    navigationViewModel: NavigationViewModel,
    suggestionDropDown: MutableState<Boolean>,

){
    val followers = PostsRepo().getPosts().takeLast(2)
    val userIndex = PostsRepo().getPosts().indexOfFirst{ it.user_name == profileOwner.user_name}
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

                ProfileInfo(
                    amount = "7",
                    info = "Posts",
                    currentPage = null,
                    userIndex = userIndex,
                    navigationViewModel = navigationViewModel
                )
                ProfileInfo(
                    navigationViewModel = navigationViewModel,
                    amount = user.followers_count.toString(),
                    info = "Followers",
                    currentPage = "1",
                    userIndex = userIndex,
                )

                ProfileInfo(
                    navigationViewModel = navigationViewModel,
                    amount = user.following_count.toString(),
                    info = "Following",
                    currentPage = "2",
                    userIndex = userIndex
                )
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