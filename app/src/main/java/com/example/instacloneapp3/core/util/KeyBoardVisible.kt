package com.example.instacloneapp3.core.util

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.runtime.Composable
import androidx.core.view.ViewCompat

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KeyboardOpen() {
    val windowInsets = WindowInsets.Companion
    val keyboardVisible = windowInsets.isImeVisible
}