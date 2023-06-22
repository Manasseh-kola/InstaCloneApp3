package com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_screen_bottom_sheets.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


@Composable
fun UsersProfileNotification(){
    val checked = remember{ mutableStateOf(true) }
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "Code")
            Switch(
                checked = checked.value,
                onCheckedChange = { checked.value = !checked.value }
            )
        }
    }
}



@Composable
@Preview(showBackground = true)
fun UsersProfileNotificationPreview(){
    InstaCloneApp3Theme {
        UsersProfileNotification()
    }
}