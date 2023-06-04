package com.example.instacloneapp3.presentation.ui.screens.authentication_screens.signup_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.ui.components.FormButton
import com.example.instacloneapp3.presentation.ui.components.FormField
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


@Composable
fun CreateUsernameScreen(){
    val username = remember{ mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ){
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clickable(onClick = {/*TODO*/ })
            )
        }

        Text(
            "Create username",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            text = "Choose a username for your new account you can always change it later",
            maxLines = 2,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(bottom = 20.dp)
            ,
        )

        FormField(input = username , placeholder = "Username", width = 0.9f)
        FormButton(text = "Next", width = 0.9f, textSize = 16.sp)



    }

}

@Composable
@Preview(showBackground = true)
fun CreateUsernameScreenPreview(){
    InstaCloneApp3Theme {
        CreateUsernameScreen()
    }
}