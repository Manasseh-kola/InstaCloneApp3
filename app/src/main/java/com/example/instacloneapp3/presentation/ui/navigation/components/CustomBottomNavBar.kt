package com.example.instacloneapp3.presentation.ui.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

sealed class NavBarScreen(
    val route: String,
    val icon : Int,
    val selectedIcon: Int
) {
    object Home : NavBarScreen("home", R.drawable.ic_outlined_home, R.drawable.ic_filled_home)
    object Search : NavBarScreen("search", R.drawable.ic_outlined_search, R.drawable.ic_outlined_search)
    object New : NavBarScreen("newPost", R.drawable.ic_outlined_add, R.drawable.ic_outlined_add)
    object Reels : NavBarScreen("reels", R.drawable.ic_outlined_reels, R.drawable.ic_outlined_reels)
    object Profile : NavBarScreen("profile", R.drawable.ic_outlined_camera, R.drawable.ic_outlined_camera)
}

val bottomTabs = listOf(
    NavBarScreen.Home,
    NavBarScreen.Search,
    NavBarScreen.New,
    NavBarScreen.Reels,
    NavBarScreen.Profile
)


@Composable
fun NavBarItem(
    route: String,
    iconId: Int,
    onNavigate: (String) -> Unit,
    currentRoute: MutableState<String>
){
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "",
            modifier = Modifier
                .size(27.dp)
                .clickable(
                    onClick = {
                        onNavigate(route)
                        currentRoute.value = route
                    }
                )
        )
}

@Composable
fun InstagramCloneNavBar(
    onNavigate: (String) -> Unit,
    navBackStackEntry: NavBackStackEntry?
){
    val currentRoute = remember{ mutableStateOf("home")}

    //Navigation bar root element
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 14.dp, vertical = 10.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        bottomTabs.forEach{ screen ->
            NavBarItem(
                route = screen.route,
                iconId = if(screen.route == currentRoute.value) screen.selectedIcon
                else screen.icon,
                onNavigate = onNavigate,
                currentRoute = currentRoute
            )
        }

    }

}


@Composable
@Preview(showBackground = true)
fun InstagramCloneNavBarPreview(){
    InstaCloneApp3Theme {
        val appState = rememberAppState()
        InstagramCloneNavBar(
            appState::onBottomNavBarNavigation,
            appState.navController.currentBackStackEntry
        )
    }
}