package com.example.instacloneapp3.presentation.view_models


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.states.CustomNavStackStates
import com.example.instacloneapp3.presentation.states.NavigationStates
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
import com.example.instacloneapp3.presentation.ui.navigation.graphs.Screens
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

    private val _customNavStackStates = MutableStateFlow(CustomNavStackStates())
    val customNavStackStates = _customNavStackStates.asStateFlow()


    //Open BottomSheet if it is closed
    fun openBottomSheet(currentBottomSheet: BottomSheets, currentScreen: AppScreenTypes){
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


    //Add to backStack
    fun addRouteToBackStack(destination: Screens){
        _customNavStackStates.value.homeStack.add(destination)
        _customNavStackStates.update{ state ->
            state.copy()
        }
    }

}