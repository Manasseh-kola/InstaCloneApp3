package com.example.instacloneapp3.presentation.ui.screens.notifications_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.components.HorizontalDraggableScreen
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.ui.screens.notifications_screen.components.AdsActivity
import com.example.instacloneapp3.presentation.ui.screens.notifications_screen.components.NotificationItem
import com.example.instacloneapp3.presentation.ui.screens.notifications_screen.components.NotificationsScreenHeader
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
Notifications Screen
 */
@Composable
fun NotificationsScreen(
    screenStackIndex: Int,
    navigationViewModel: NavigationViewModel,
){

    val list = PostsRepo().getPosts()
    val activities = list + list

    HorizontalDraggableScreen(
        screenStackIndex = screenStackIndex,
        navigationViewModel = navigationViewModel
    ){
        Surface(shadowElevation = 10.dp){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                NotificationsScreenHeader { navigationViewModel.popBackStack() }

                //--Lazy List for notification items--
                LazyColumn {
                    item { AdsActivity() }
                    items(activities) { activity ->
                        NotificationItem(activity)
                    }
                }
            }
        }
    }
}



@Composable
@Preview(showBackground = true)
fun NotificationsScreenPreview(){
    InstaCloneApp3Theme {
        NotificationsScreen(
            screenStackIndex = 0,
            navigationViewModel = hiltViewModel()
        )
    }
}