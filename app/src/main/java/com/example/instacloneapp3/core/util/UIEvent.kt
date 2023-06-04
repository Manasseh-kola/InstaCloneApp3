package com.example.instacloneapp3.core.util

sealed class UIEvent {
    data class ShowSnackbar(val message: String): UIEvent()
}