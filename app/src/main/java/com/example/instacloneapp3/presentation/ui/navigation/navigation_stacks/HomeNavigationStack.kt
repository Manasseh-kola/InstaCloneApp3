package com.example.instacloneapp3.presentation.ui.navigation.navigation_stacks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.navigation.graphs.AppScreens
import com.example.instacloneapp3.presentation.ui.navigation.graphs.HomeScreensMapper
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeScreensEntryPoint(
    navigateToRoute: (String) -> Unit,
    modalState: MutableState<Boolean>,
    navigationViewModel: NavigationViewModel,
    backNavigation: (String, String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
){

    val second = remember{ mutableStateOf(false)}
    val navigationUiState = navigationViewModel.navigationState.collectAsState()
    navigationViewModel.setStackRoot(AppScreens.Home)
    val backStack = navigationUiState.value.currentStack

    LaunchedEffect(key1 = navigationUiState.value.newScreenOnStack){
        delay(500)
        second.value = true
    }


    //Add Home Screen as first element to stack
    if (backStack.size == 0){
        navigationViewModel.addRouteToBackStack(AppScreens.Home)
    }

    //Display Last two Screens
    Box {
        when( backStack.size){
            1 ->{
                HomeScreensMapper(
                    modalState = modalState,
                    backNavigation = backNavigation,
                    navigateToRoute = navigateToRoute,
                    currentModalSheet = currentModalSheet,
                    navigationViewModel = navigationViewModel,
                    destination = backStack[backStack.lastIndex],

                )
            }
            else ->{
                HomeScreensMapper(
                    modalState = modalState,
                    backNavigation = backNavigation,
                    navigateToRoute = navigateToRoute,
                    currentModalSheet = currentModalSheet,
                    navigationViewModel = navigationViewModel,
                    destination = backStack[backStack.lastIndex-1],
                )

                AnimatedVisibility(
                    visible = second.value,
                    enter = slideInHorizontally(),
                    exit = slideOutHorizontally(),
                ) {
                    HomeScreensMapper(
                        modalState = modalState,
                        backNavigation = backNavigation,
                        navigateToRoute = navigateToRoute,
                        currentModalSheet = currentModalSheet,
                        navigationViewModel = navigationViewModel,
                        destination = backStack[backStack.lastIndex],
                    )
                }

            }
        }
    }
}

