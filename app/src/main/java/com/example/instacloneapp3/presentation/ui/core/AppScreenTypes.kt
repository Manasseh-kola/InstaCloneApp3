package com.example.instacloneapp3.presentation.ui.core

sealed class AppScreenTypes(val name: String) {
    object Home: AppScreenTypes("home")
    object Search: AppScreenTypes("search")
    object New: AppScreenTypes("newPost")
    object Reels: AppScreenTypes("reels")
    object Profile: AppScreenTypes("profile")
}