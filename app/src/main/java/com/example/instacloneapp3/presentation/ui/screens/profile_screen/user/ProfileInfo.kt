package com.example.instacloneapp3.presentation.ui.screens.profile_screen.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.screens.home_screen.storyImageModifier
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
User Profile info section
 */



@Composable
fun ProfileInfo(
    amount: String,
    info: String,
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
                - page 1 = Followers screen
                - page 2 = Following screen
                 */
                if(currentPage != null) {
                    navigationViewModel.pushToBackStack(
                        AppScreenTypes.UserRelationShip(args = currentPage)
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
fun ProfileInfoTab(navigationViewModel: NavigationViewModel) {

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

            //Profile Photo and Add to Story button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable{}
            ) {
                Box {

                    //User Profile Photo
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = user.profile_picture),
                        contentDescription = "",
                        modifier = storyImageModifier
                    )

                    //Add button
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


            //RelationShip/Posts Information
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .width(250.dp)
                    .offset(y = 10.dp)
                    .padding(5.dp)
            ) {
                //Number of Posts
                ProfileInfo(
                    amount = "7",
                    info = "Posts",
                    currentPage = null,
                    navigationViewModel = navigationViewModel
                )

                //Number of Followers
                ProfileInfo(
                    navigationViewModel = navigationViewModel,
                    amount = user.followers_count.toString(),
                    info = "Followers",
                    currentPage = "0",
                )

                //Number of User Following
                ProfileInfo(
                    navigationViewModel = navigationViewModel,
                    amount = user.following_count.toString(),
                    info = "Following",
                    currentPage = "1",
                )
            }

        }

        //User name
        Text(
            text = user.user_name,
            fontWeight = FontWeight.Bold,
        )

        //User bio
        Text(
            text = user.bio,
        )


        //Edit and share profile buttons
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {

            //Edit Profile Button
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.buttonColors(Color(213, 212, 212)),
                modifier = Modifier
                    .weight(1F)
                    .padding(end = 2.dp),
            ) {
                Text(text = "Edit Profile", color = Color.Black)
            }

            //Share Profile Button
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.buttonColors(Color(213, 212, 212)),
                modifier = Modifier
                    .weight(1F)
                    .padding(start = 2.dp),
            ) {
                Text(text = "Share Profile", color = Color.Black)
            }
        }


        //Saved Story Highlights
        StoryHighlightsDropDown()
    }
}


@Composable
@Preview(showBackground = true)
fun ProfileInfoPreview(){
    InstaCloneApp3Theme {
        ProfileInfoTab(navigationViewModel =  hiltViewModel())
    }
}