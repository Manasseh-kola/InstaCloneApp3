package com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_screen_bottom_sheets.user.try_new_account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.ui.screens.home_screen.LikedBy
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

@Composable
fun CreateSmallerGroup(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Spacer(Modifier.padding(top = 20.dp))
        LikedBy()
        Text(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = "Keep up with a smaller group of friends",
            modifier = Modifier.padding(bottom = 8.dp, top = 10.dp)
        )
        Text(
            lineHeight = 22.sp,
            text = "Create another account to stay in touch with a group of your friends",
            textAlign = TextAlign.Center,
            modifier = Modifier.height(45.dp)
        )

        TryNewAccountButton(text = "Try a New Account")
    }

}

@Composable
@Preview(showBackground = true)
fun CreateSmallerGroupPreview(){
    InstaCloneApp3Theme {
        CreateSmallerGroup()
    }
}