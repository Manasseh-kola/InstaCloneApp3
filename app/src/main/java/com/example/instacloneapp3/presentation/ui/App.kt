package com.example.instacloneapp3.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
//import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
//import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.instacloneapp3.presentation.ui.navigation.components.InstagramCloneNavBar
import com.example.instacloneapp3.presentation.ui.navigation.graphs.Screen
import com.example.instacloneapp3.presentation.ui.navigation.graphs.authenticatedGraph
import com.example.instacloneapp3.presentation.ui.bottom_sheets.AppBottomSheets
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.modals.AppModalSheets
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.screens.direct_message_screen.DirectMessageScreen
import com.example.instacloneapp3.presentation.ui.screens.story_screen.StoryScreen
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

val tabs = listOf(
    Screen.Home,
    Screen.Search,
    Screen.New,
    Screen.Reels,
    Screen.Profile
)

val homeTabs = setOf(
    "home",
    "search",
    "newPost",
    "reels",
    "profile"
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun App() {
    val appState = rememberAppState()
    val navController = appState.navController
    val modalState = appState.modalState
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    val showBottomSheet = remember { mutableStateOf(false) }
    val currentBottomSheet = remember{ mutableStateOf(BottomSheets.NO_SHEET)}
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val currentModalSheet = remember{ mutableStateOf(ModalSheets.NO_SHEET)}

    Box(
        modifier = Modifier.fillMaxSize()
    ){

        //Navigation
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                AppModalSheets(currentModalSheet)
            }
        ){
            Scaffold(
                bottomBar = {
                    if (currentDestination?.route in homeTabs) {
                        InstagramCloneNavBar(
                            onNavigate = appState::onBottomNavBarNavigation,
                            navBackStackEntry = navBackStackEntry
                        )
                    }
                }

            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "authenticated",
                    modifier = Modifier
                        .padding(innerPadding)
                ) {
                    authenticatedGraph(
                        appState::onNavigateToScreen,
                        appState::backNavigation,
                        showBottomSheet,
                        currentBottomSheet,
                        currentModalSheet,
                        drawerState
                    )
                }


                if (showBottomSheet.value) {
                    AppBottomSheets(
                        sheetState = sheetState,
                        showBottomSheet = showBottomSheet,
                        currentBottomSheet = currentBottomSheet
                    )
                }
            }
        }


        //Modals (No Bottom Navigation Bar)
        AnimatedVisibility(
            visible = modalState.value,
            enter = when(currentDestination?.route){
                "home" -> expandIn()
                "messages" -> expandHorizontally(expandFrom = Alignment.Start)
                else -> {
                    expandIn()}
            },
            exit = when(currentDestination?.route){
                "home" -> scaleOut()
                "messages" -> shrinkHorizontally(shrinkTowards = Alignment.End)
                else -> {
                    scaleOut()
                }
            },

            ){
            when(currentDestination?.route){
                "home" -> StoryScreen(appState::hideModal)
                "messages" -> DirectMessageScreen(appState::hideModal)
            }
        }


    }
}

@Composable
@Preview(showBackground = true)
fun AppPreview(){
    InstaCloneApp3Theme() {
       App()
    }
}