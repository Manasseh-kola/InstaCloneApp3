package com.example.instacloneapp3.presentation.ui.screens.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

@Composable
fun DropDown(
    modifier: Modifier,
    currentHomeModal: MutableState<HomeModals>,
    showHomeModal: ()-> Unit
){

    var expand = remember { mutableStateOf(false) }
    val icon = if (expand.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        Icon(
            painter = painterResource(id = R.drawable.instaclone_logo) ,
            contentDescription = "")
        Spacer(Modifier.size(5.dp))

        Icon(icon,
            "",
            Modifier.clickable { expand.value = !expand.value }
        )
        DropdownMenu(
            expanded = expand.value,
            onDismissRequest = { expand.value = false},
            modifier = Modifier.alpha(0.9f)
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        "Following",
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(end = 10.dp)
                    )
                },
                onClick = { /*TODO*/ },
                trailingIcon = {
                    Icon(
                        Icons.Outlined.Person,
                        "",
                        modifier = Modifier
                            .padding(end = 18.dp)
                    )
                }
            )
            Divider()
            DropdownMenuItem(
                text = {
                    Text(
                        "Favourites",
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(end = 10.dp)
                    )
                },
                onClick = { /*TODO*/ },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outlined_favorite ),
                        "",
                        modifier = Modifier
                            .padding(end = 18.dp)
                            .clickable{
                                expand.value = false
                                showHomeModal()
                            }
                    )
                }
            )
        }
    }
}


@Composable
fun TopBar(
    navigateToRoute: (String) -> Unit,
    backNavigation: (String, String) -> Unit,
    modalVisible: MutableState<Boolean>,
    currentModalSheet: MutableState<ModalSheets>,
    currentHomeModal: MutableState<HomeModals>,
    showModal: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        DropDown(
            Modifier
                .height(45.dp)
                .weight(4F),
            currentHomeModal
        ){
            currentHomeModal.value = HomeModals.FAVOURITES_SCREEN
            showModal()
        }

        Row(
            modifier = Modifier
                .weight(1F)
        )
        {
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