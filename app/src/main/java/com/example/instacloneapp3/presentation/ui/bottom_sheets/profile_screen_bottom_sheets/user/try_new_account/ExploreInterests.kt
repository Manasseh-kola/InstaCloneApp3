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
import com.example.instacloneapp3.presentation.ui.screens.home_screen.components.LikedBy
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme

@Composable
fun ExploreInterests(){
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
            text = "Dive into what you love",
            modifier = Modifier.padding(bottom = 8.dp, top = 10.dp)
        )
        Text(
            "Create another account to explore your interests.",
            lineHeight = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.height(45.dp)
        )

        TryNewAccountButton(text = "Try a New Account")
    }

}

@Composable
@Preview(showBackground = true)
fun ExploreInterestsPreview(){
    InstaCloneApp3Theme {
        ExploreInterests()
    }
}