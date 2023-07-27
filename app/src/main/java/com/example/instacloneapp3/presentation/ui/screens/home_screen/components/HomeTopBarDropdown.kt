package com.example.instacloneapp3.presentation.ui.screens.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

@Composable
fun DropDown(
    modifier: Modifier,
    navigationViewModel: NavigationViewModel,
    ){



    //State of Dropdown menu
    val isDropdownExpanded = remember { mutableStateOf(false) }
    val icon = if (isDropdownExpanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){

        //Instagram Logo
        Icon(
            painter = painterResource(id = R.drawable.instaclone_logo) ,
            contentDescription = ""
        )

        Spacer(Modifier.size(5.dp))

        //Dropdown arrow
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier.clickable {
                isDropdownExpanded.value = !isDropdownExpanded.value
            }
        )

        //Dropdown menu
        DropdownMenu(
            expanded = isDropdownExpanded.value,
            onDismissRequest = { isDropdownExpanded.value = false},
            modifier = Modifier.alpha(0.9f)
        ) {

            //Following Feeds
            DropdownMenuItem(
                text = {
                    Text(
                        fontSize = 14.sp,
                        text = "Following",
                        modifier = Modifier.padding(end = 10.dp)
                    )
                },
                onClick = { /*TODO*/ },
                trailingIcon = {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = "",
                        modifier = Modifier.padding(end = 18.dp)
                    )
                }
            )

            Divider()

            //Favourites Feeds
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Favourites",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 10.dp)
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
                                navigationViewModel.pushToBackStack(AppScreenTypes.FavoriteFeeds)
                            }
                    )
                }
            )
        }
    }
}