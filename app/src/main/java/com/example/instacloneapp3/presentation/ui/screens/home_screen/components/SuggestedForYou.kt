package com.example.instacloneapp3.presentation.ui.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme

/*
Suggested Accounts to interact with
 */
@Composable
fun SuggestedForYou(
    itemWidth: Dp = LocalConfiguration.current.screenWidthDp.dp / 2
){
    val list = PostsRepo().getPosts()
    val suggestions = list + list

    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ){
            Text(text = "Suggested for you")
            Text(
                text = "See all",
                color = Color(0.012f, 0.663f, 0.957f)
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(suggestions){suggestion ->
                SuggestedItem(suggestion, itemWidth)
            }
        }
    }
}

@Composable
fun SuggestedItem(
    suggestion: Posts,
    width: Dp = 0.dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(width)
            .border(1.dp,Color.LightGray, RoundedCornerShape(10.dp)),
    ){

        //--Close Suggestion--
        Box( modifier = Modifier.fillMaxWidth() ){
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .size(18.dp)
                    .align(Alignment.TopEnd)
            )
        }

        //--Profile picture of suggested account--
        Image(
            painter = painterResource(id = suggestion.profile_picture),
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier
                .size(width * 2 / 3)
                .clip(CircleShape)
        )

        //--Username of suggested account--
        Text(
            fontWeight = FontWeight(700),
            overflow = TextOverflow.Ellipsis,
            text = suggestion.user_name,
            maxLines = 1,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )
        Text(text = "Follows you")

        //--Action Button--
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0.012f, 0.663f, 0.957f))
                .padding(vertical = 8.dp)
        ){
            Text(
                text = "Follow Back",
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }


    }
}

@Composable
@Preview(showBackground = true)
fun SuggestedForYouPreview(){
    InstaCloneApp3Theme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            SuggestedForYou()
        }
    }
}