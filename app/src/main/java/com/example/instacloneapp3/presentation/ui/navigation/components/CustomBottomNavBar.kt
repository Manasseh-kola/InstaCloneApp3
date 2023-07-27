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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

val bottomTabs = listOf(
    AppScreenTypes.Home(),
    AppScreenTypes.Search(),
    AppScreenTypes.New,
    AppScreenTypes.Reels(),
    AppScreenTypes.UserProfile()
)

@Composable
fun NavBarItem(
    selected: Boolean,
    screen: AppScreenTypes,
    onNavigate: (String) -> Unit,
    navigationViewModel: NavigationViewModel,
){
        val iconId = if(selected) screen.selectedIcon
        else screen.icon
        Icon(
            painter = painterResource(id = iconId!!),
            contentDescription = "",
            modifier = Modifier
                .size(if(screen.route!= "reels") 35.dp else 33.dp)
                .clickable{
                    navigationViewModel.selectStack(screen)
                    onNavigate(screen.route)
                }
        )
}

@Composable
fun InstagramCloneNavBar(
    onNavigate: (String) -> Unit,
    navBackStackEntry: NavBackStackEntry?,
    navigationViewModel: NavigationViewModel
){

    val currentDestination = navBackStackEntry?.destination

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
                screen = screen,
                onNavigate = onNavigate,
                navigationViewModel = navigationViewModel,
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
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
            navigationViewModel = hiltViewModel(),
            onNavigate = appState::onBottomNavBarNavigation,
            navBackStackEntry = appState.navController.currentBackStackEntry,
        )
    }
}