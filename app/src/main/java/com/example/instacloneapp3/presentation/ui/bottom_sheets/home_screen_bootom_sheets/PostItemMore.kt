package com.example.instacloneapp3.presentation.ui.bottom_sheets.home_screen_bootom_sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

@Composable
fun MoreItem(text: String, icon: ImageVector){
    Row(){
        Icon(imageVector = icon, contentDescription = "")
        Spacer(Modifier.width(20.dp))
        Text(
            text = text
        )
    }
}

@Composable
fun PostItemMore(){

    val lightGray = Color(235, 235, 235)
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()

    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(lightGray)
                    .padding(10.dp)
                ,
                horizontalAlignment  = Alignment.CenterHorizontally

            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "",


                )
                Text(text= "Save")
            }

            Column(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(lightGray)
                    .padding(10.dp),
                horizontalAlignment  = Alignment.CenterHorizontally

            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "",


                    )
                Text(text= "Save")
            }

        }

        //We moved things around
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(lightGray)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(){
                Icon(imageVector = Icons.Default.Send, contentDescription = "")
                Spacer(Modifier.width(20.dp))
                Column() {
                    Text(
                        text = "We moved things around"
                    )
                    Text(
                        text = "See where to share and copy"
                    )
                }
            }
            Icon(imageVector = Icons.Default.Close, contentDescription = "")
        }

        //Add to favourites and unfollow
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(lightGray)
                .padding(10.dp)
        ){
            MoreItem(
                text = "Add to Favourites",
                icon = Icons.Default.Favorite
            )

            Divider(Modifier.padding(vertical = 20.dp))

            MoreItem(
                text = "Unfollow",
                icon = Icons.Default.Person
            )

        }


        //About this account
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(lightGray)
                .padding(20.dp)
        ){
            MoreItem(
                text = "About this account",
                icon = Icons.Filled.AccountCircle
            )

            Divider(Modifier.padding(vertical = 20.dp))

            MoreItem(
                text = "QR code",
                icon = Icons.Default.AccountBox
            )

            Divider(Modifier.padding(vertical = 20.dp))

            MoreItem(
                text = "Why are you seeing this post" ,
                icon = Icons.Default.Delete
            )

            Divider(Modifier.padding(vertical = 20.dp))

            MoreItem(
                text = "Hide" ,
                icon = Icons.Default.LocationOn
            )

            Divider(Modifier.padding(vertical = 20.dp))

            MoreItem(
                text = "Report" ,
                icon = Icons.Default.Warning
            )
        }


    }

}

@Composable
@Preview(showBackground = true)
fun PostItemMorePreview(){
    InstaCloneApp3Theme {
        PostItemMore()
    }
}