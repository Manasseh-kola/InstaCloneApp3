package com.example.instacloneapp3.presentation.ui.screens.authentication_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


@Composable
fun ResetPasswordScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "ResetPasswordScreen")
    }
}

@Composable
@Preview(showBackground = true)
fun ResetPasswordScreenPreview(){
    InstaCloneApp3Theme {
        ResetPasswordScreen()
    }
}