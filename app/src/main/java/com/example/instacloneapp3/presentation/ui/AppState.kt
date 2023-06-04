package com.example.instacloneapp3.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    modalState: MutableState<Boolean> = remember { mutableStateOf(false) }
) =
    remember(navController, modalState){
        AppState(navController, modalState)
    }

@Stable
class AppState(
    val navController: NavHostController,
    val modalState: MutableState<Boolean>
){

    //Events
    fun onNavigateToScreen(route: String) {
        if (route == "story" || route == "directMessage"){
            modalState.value = true
        } else {
            navController.navigate(route) {
                launchSingleTop = true
            }
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

    fun hideModal(){
        modalState.value = false
    }




}