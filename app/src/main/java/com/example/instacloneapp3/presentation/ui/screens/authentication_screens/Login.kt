package com.example.instacloneapp3.presentation.ui.screens.authentication_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.ui.components.FormField
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


@Composable
fun LoginForm(){
    val email = remember { mutableStateOf("")}
    val password = remember { mutableStateOf("")}
    val passwordIcon = Icons.Default.Edit
    val blueColor  = Color(55, 151, 239, 255)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormField(input = email, placeholder = "Email", width = 0.95f)
        FormField(input = password, placeholder = "Password", leadingIcon = passwordIcon,width = 0.95f )
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
        ){
            Text(
                text = "Forgot password?", color = blueColor,
                fontSize = 13.sp, fontWeight = FontWeight.Bold
            )
        }

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(blueColor),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 20.dp),
            shape = RoundedCornerShape(8)

        ) {
            Text(
                "Log in", color = Color.White, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        Row(
            modifier = Modifier.padding(vertical = 20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                tint = blueColor,
                painter = painterResource(id = R.drawable.facebook_logo),
                contentDescription = "",
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                "Log in with Facebook",
                color = blueColor,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
fun LoginScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
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
                    .clickable(onClick = {/*TODO*/})
                )
        }
        Image(
            painter = painterResource(id = R.drawable.instaclone_logo),
            contentDescription = "Instagram logo",
            modifier = Modifier.padding(top = 60.dp, bottom = 20.dp)
        )

        LoginForm()

        Box(
            modifier = Modifier.padding(vertical = 20.dp)
        ){
            Divider(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .align(Alignment.Center),
            )

            Text(
                "OR",
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .background(Color.White)
                    .align(Alignment.Center),
            )
        }

        Row(
            modifier = Modifier.padding(top = 40.dp, bottom = 60.dp)
        ){
            Text(text = "Don't have an account?", color = Color.Gray, fontWeight = FontWeight.Bold)
            Text(
                text = "Sign up",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable(onClick = {/*TODO*/})
            )
        }

        Divider()
        Text(
            "Instagram from Meta", color = Color.Gray,
            modifier = Modifier.padding(top = 20.dp)
        )

    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview(){
    InstaCloneApp3Theme {
        LoginScreen()
    }
}
