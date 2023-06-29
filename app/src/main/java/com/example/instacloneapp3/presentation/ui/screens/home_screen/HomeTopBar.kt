package com.example.instacloneapp3.presentation.ui.screens.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

/*
Home Feed Screen top bar
 */


@Composable
fun TopBar(
    navigateToRoute: (String) -> Unit,
    backNavigation: (String, String) -> Unit,
    modalVisible: MutableState<Boolean>,
    currentModalSheet: MutableState<ModalSheets>,
    currentHomeModal: MutableState<HomeModals>,
    showModal: () -> Unit
) {

    //Top Bar root composable
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){

        //Instagram Logo and Feeds Dropdown menu
        DropDown(
            Modifier
                .height(45.dp)
                .weight(4F),
            currentHomeModal
        ){
            currentHomeModal.value = HomeModals.FAVOURITES_SCREEN
            showModal()
        }

        //Top Bar Icons
        Row(
            modifier = Modifier
                .weight(1F)
        )
        {
            //Icon to navigate to Notification Screen
            Icon(
                painter = painterResource(id = R.drawable.ic_outlined_favorite ),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .weight(1F)
                    .clickable {
                        currentHomeModal.value = HomeModals.NOTIFICATIONS_SCREEN
                        showModal()
                    }
            )

            //Icon to navigate to Messages Screen
            Icon(
                painter = painterResource(id = R.drawable.ic_dm),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .weight(1F)
                    .clickable(onClick = { navigateToRoute("messages") })
            )

        }

    }
}

@Composable
@Preview(showBackground = true)
fun TopBarPreview(){
    val appstate = rememberAppState()
    InstaCloneApp3Theme() {
        TopBar(
            appstate::onNavigateToScreen,
            appstate::backNavigation,
            remember{ mutableStateOf(false)},
            remember{ mutableStateOf(ModalSheets.NO_SHEET)},
            remember{ mutableStateOf(HomeModals.NO_SCREEN)}
        ){}
    }
}