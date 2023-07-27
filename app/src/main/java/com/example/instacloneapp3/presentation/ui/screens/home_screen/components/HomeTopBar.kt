package com.example.instacloneapp3.presentation.ui.screens.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
Home/Feed Screen top bar
 */

@Composable
fun TopBar(
    navigationViewModel: NavigationViewModel,
    onClick: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){

        //--Instagram Logo and Feeds Dropdown menu--
        DropDown(
            navigationViewModel = navigationViewModel,
            modifier = Modifier
                .height(45.dp)
                .weight(4F),
        )

        //--Top Bar Icons/Buttons--
        Row(
            modifier = Modifier
                .weight(1F)
        )
        {
            //Icon/Button to navigate to Notification Screen
            Icon(
                painter = painterResource(id = R.drawable.ic_outlined_favorite ),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .weight(1F)
                    .clickable {
                        navigationViewModel.pushToBackStack(AppScreenTypes.Notifications)
                    }
            )

            //Icon/Button to navigate to Messages Screen
            Icon(
                painter = painterResource(id = R.drawable.ic_dm),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .weight(1F)
                    .clickable { onClick() }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TopBarPreview(){
    InstaCloneApp3Theme {
        TopBar( navigationViewModel = hiltViewModel()){}
    }
}