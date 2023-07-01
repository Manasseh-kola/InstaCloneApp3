package com.example.instacloneapp3.presentation.states

import com.example.instacloneapp3.presentation.ui.navigation.graphs.Screens
data class CustomNavStackStates(
    val homeStack: MutableList<Screens> = mutableListOf(),
)
