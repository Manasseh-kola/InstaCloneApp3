package com.example.instacloneapp3.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.instacloneapp3.presentation.mock_data.Posts
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


    //--Open BottomSheet if it is closed--
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

    //--Close BottomSheet if it is opened--
    fun closeBottomSheet(){
        if (!_navigationState.value.showBottomSheet) return
        _navigationState.update { currentNavigationState ->
            currentNavigationState.copy(showBottomSheet = false)
        }
    }

    //--Add a currentUser--
    fun addCurrentUser(currentUser: Posts?){
        _navigationState.update { currentNavigationState ->
            currentNavigationState.copy(currentUser = currentUser)
        }
    }

    //-- Set value of screenXOffset and prevScreenXOffset --
    fun setScreenXOffset(offset: Float){
        _navigationState.update{state ->
            state.copy(
                screenXOffset = offset,
                prevScreenXOffset = -offset / 8
            )
        }
    }

    //--Update XOffset for top and bottom Screen--
    fun updateTopScreenXOffset(delta: Float){
        val currentXOffset = _navigationState.value.topScreenXOffset
        val prevXOffset = _navigationState.value.prevScreenXOffset
        if ( currentXOffset + delta >= 0.0f ){
            _navigationState.update { state ->
                state.copy(
                    topScreenXOffset = currentXOffset + delta,
                    prevScreenXOffset = prevXOffset + delta / 8
                )
            }
        }
    }

    //--Reset XOffsets--
    fun resetScreenXOffset(){
        val screenXOffset = _navigationState.value.screenXOffset
        _navigationState.update { state -> state.copy(
            topScreenXOffset = 0.0f,
            prevScreenXOffset = -screenXOffset / 8,
            prevScreenIndex = -1,
        ) }
    }

    fun cleanUpXOffset(){
        _navigationState.update{ state -> state.copy(
            topScreenXOffset = 0.0f,
        )}
    }

    private fun setPrevScreenIndex(){
        val nextPrevScreenIndex = _navigationState.value.currentStack.lastIndex - 1
        _navigationState.update { state ->
            state.copy(
                prevScreenXOffset = 0.0f,
                prevScreenIndex = nextPrevScreenIndex
            )
        }
    }

    fun horizontalScreenDragEnded(xBreakPoint: Float){
        val screenXOffset = _navigationState.value.screenXOffset
        val currentXOffset = _navigationState.value.topScreenXOffset
        if(currentXOffset > xBreakPoint){
            setPrevScreenIndex()
            popBackStack()
        }
        else{
            _navigationState.update { state ->
                state.copy(
                    topScreenXOffset = 0.0f,
                    prevScreenXOffset = -screenXOffset / 8f,
                )
            }
        }
    }

    //--Set root screen of current stack--
    private fun setStackRoot(root: AppScreenTypes){
        _navigationState.update { state -> state.copy(currentStackRoot = root) }
    }

    //--Select current stack--
    fun selectStack(rootScreen: AppScreenTypes){
        setStackRoot(rootScreen)
        val currentStack = when(_navigationState.value.currentStackRoot){
            is AppScreenTypes.Home -> _navigationState.value.homeStack
            is AppScreenTypes.New -> _navigationState.value.newPostStack
            is AppScreenTypes.Reels -> _navigationState.value.reelsStack
            is AppScreenTypes.Search -> _navigationState.value.searchStack
            is AppScreenTypes.Messages -> _navigationState.value.messagesStack
            is AppScreenTypes.UserProfile -> _navigationState.value.userProfileStack
            else -> {_navigationState.value.homeStack}
        }
        _navigationState.update { state -> state.copy(currentStack = currentStack)}
    }

    //--Push new screen on stack--
    fun pushToBackStack(screenRoute: AppScreenTypes){
        _navigationState.value.currentStack.add(screenRoute)
    }

    //--Pop top of stack--
    fun popBackStack(){
        val currentStack = _navigationState.value.currentStack
        navigationState.value.currentStack.removeAt(currentStack.lastIndex)
    }

}