package com.example.instacloneapp3.presentation.ui.navigation.graphs

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.instacloneapp3.presentation.ui.screens.home_screen.HomeScreen
import com.example.instacloneapp3.presentation.ui.screens.MessagesScreen
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.ProfileScreen
import com.example.instacloneapp3.presentation.ui.screens.ReelsScreen
import com.example.instacloneapp3.presentation.ui.screens.SearchScreen
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.RelationShipScreen


sealed class Screen(val route: String, val icon : ImageVector) {
    object Home : Screen("home", Icons.Outlined.Home)
    object Search : Screen("search", Icons.Outlined.Search)
    object New : Screen("newPost", Icons.Outlined.AddCircle)
    object Reels : Screen("reels", Icons.Outlined.Phone)
    object Profile : Screen("profile", Icons.Outlined.Person)
    object Messages: Screen("messages", Icons.Outlined.MailOutline)
    object Relationship: Screen("relationship/{currentPage}", Icons.Outlined.MailOutline)

}


fun NavGraphBuilder.authenticatedGraph(
    navigateToRoute: (String) -> Unit,
    backNavigation: (String, String) -> Unit,
    showBottomSheet: MutableState<Boolean>,
    currentBottomSheet: MutableState<BottomSheets>,
    currentModalSheet: MutableState<ModalSheets>,
    drawerState: DrawerState,
){
    navigation(startDestination = "home", route = "authenticated"){
        composable(Screen.Home.route){ HomeScreen(navigateToRoute,backNavigation, currentModalSheet, drawerState) }
        composable(Screen.Search.route){ SearchScreen() }
        composable(Screen.New.route){ Row(){ Text(text = "Add new") } }
        composable(Screen.Reels.route){ ReelsScreen(navigateToRoute,backNavigation) }
        composable(Screen.Profile.route){ ProfileScreen(navigateToRoute, showBottomSheet, currentBottomSheet) }
        composable(Screen.Messages.route){ MessagesScreen(backNavigation, navigateToRoute) }
        composable(
            Screen.Relationship.route,
            arguments = listOf(navArgument("currentPage") { type = NavType.StringType })
        ){ backStackEntry ->
            RelationShipScreen(backNavigation, backStackEntry.arguments?.getString("currentPage"))
        }
    }
}






