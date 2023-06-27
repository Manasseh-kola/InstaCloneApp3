package com.example.instacloneapp3.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.instacloneapp3.presentation.states.NavigationStates
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.core.AppScreenTypes
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
    fun openBottomSheet(){
        if (_navigationState.value.showBottomSheet) return

        _navigationState.update { currentNavigationState ->
            currentNavigationState.copy(showBottomSheet = true)
        }

    }

    //Close BottomSheet if it is opened
    fun closeBottomSheet(){
        if (!_navigationState.value.showBottomSheet) return

        _navigationState.update { currentNavigationState ->
            currentNavigationState.copy(showBottomSheet = false)
        }

    }

    //Set value of current BottomSheet
    fun setCurrentBottomSheet(currentBottomSheet: BottomSheets){
        _navigationState.update { currentNavigationState ->
            currentNavigationState.copy(currentBottomSheet = currentBottomSheet)
        }
    }

    //Set Current Scoped Screen
    fun setCurrentScreen(currentScreen: AppScreenTypes){
        if(_navigationState.value.currentScreen == currentScreen) return
        _navigationState.update { currentNavigationState ->
            currentNavigationState.copy(currentScreen = currentScreen)
        }
    }

}