package com.example.instacloneapp3.presentation.view_models


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.states.NavigationStates
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.navigation.graphs.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _navigationState = MutableStateFlow(NavigationStates())
    val navigationState = _navigationState.asStateFlow()


    //Open BottomSheet if it is closed
    fun openBottomSheet(currentBottomSheet: BottomSheets, currentScreen: AppScreens){
        if (_navigationState.value.showBottomSheet) return
        _navigationState.update { currentNavigationState ->
            currentNavigationState.copy(
                currentScreen = currentScreen,
                currentBottomSheet = currentBottomSheet,
                showBottomSheet = true
            )
        }
    }

    //Close BottomSheet if it is opened
    fun closeBottomSheet(){
        if (!_navigationState.value.showBottomSheet) return
        _navigationState.update { currentNavigationState ->
            currentNavigationState.copy(showBottomSheet = false)
        }
    }


    //Add a currentUser
    fun addCurrentUser(currentUser: Posts?){
        _navigationState.update { currentNavigationState ->
            currentNavigationState.copy(currentUser = currentUser)
        }
    }


    //Set currentStack Root
    fun setStackRoot(root: AppScreens){
        when(root){
            AppScreens.Home -> {
                val homeStack = _navigationState.value.homeStack
                _navigationState.update { state ->
                    state.copy(
                        currentStackRoot = root,
                        currentStack = homeStack,
                    )
                }
            }
            else ->{}
        }
    }

    //Add to backStack
    fun addRouteToBackStack(destination: AppScreens){
        _navigationState.value.currentStack.add(destination)
        _navigationState.update{ state -> state.copy(
            updateStack = !_navigationState.value.updateStack,
            newScreenOnStack = !_navigationState.value.newScreenOnStack,
        ) }
    }

    //Remove from backStack
    fun popBackStack(){
        val currentStack = _navigationState.value.currentStack
        if(currentStack.size == 0) return
        currentStack.removeAt(currentStack.lastIndex)
        _navigationState.update { state -> state.copy(updateStack = !_navigationState.value.updateStack )}
    }

}