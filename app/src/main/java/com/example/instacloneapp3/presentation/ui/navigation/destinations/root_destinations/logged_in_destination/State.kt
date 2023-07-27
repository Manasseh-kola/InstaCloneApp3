package com.example.instacloneapp3.presentation.ui.navigation.destinations.root_destinations.logged_in_destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberState(
    navController: NavHostController = rememberNavController()
) =
    remember(navController) { State(navController) }


@Stable
class State(
    val navController: NavHostController,
){

    //Events
    fun onNavigateToScreen(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
        }

    }

    fun onBottomNavBarNavigation(route: String){
        navController.navigate(route) {

            /*
             Pop up to the start destination of the graph to
             avoid building up a large stack of destinations
             on the back stack as users select items
             */
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when re-selecting the same item
            launchSingleTop = true

            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
    }

    fun backNavigation(prevRoute: String, route: String){
        navController.navigate(route){
            popUpTo(prevRoute)
        }
    }

}