package com.example.instacloneapp3.presentation.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.instacloneapp3.core.util.UILoadState
import com.example.instacloneapp3.domain.usecase.GetUserUseCase
import com.example.instacloneapp3.presentation.states.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
//    savedStateHandle : SavedStateHandle
) : ViewModel(){


    init {
//        savedStateHandle.get<String>("email")?.let {email ->
//            getUser(email)
//        }
        getUser("david@gmail.com")
    }

    //States
    private val _userState = mutableStateOf(UserState())
    val userState = _userState

    //Functions
    private fun getUser(email : String){
        getUserUseCase(email).onEach { result ->
            when(result){
                is UILoadState.Success -> {
                    _userState.value = UserState(user = result.data)
                }
                is UILoadState.Error -> {
                    _userState.value = UserState(
                        error = "An unexpected error occured"
                    )
                }
                is UILoadState.Loading -> {
                    _userState.value = UserState(
                        isLoading = true
                    )
                }
            }

        }
    }



}