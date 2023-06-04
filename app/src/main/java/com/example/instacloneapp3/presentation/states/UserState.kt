package com.example.instacloneapp3.presentation.states

import com.example.instacloneapp3.data.network.models.User

data class UserState (
    val user: User? = null,
    val isLoading : Boolean = false,
    val error : String = ""
)