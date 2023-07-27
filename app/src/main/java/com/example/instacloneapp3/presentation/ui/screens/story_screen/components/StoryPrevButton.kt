package com.example.instacloneapp3.presentation.ui.screens.story_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PrevStoryButton(
    currentPage: MutableState<Int>,
    keyboardVisible:Boolean,
    modifier: Modifier
){
    var keyBoardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(196.dp)
            .background(color = Color.Transparent)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {
                    if(keyboardVisible){
                        keyBoardController?.hide()
                    }
                    else if (currentPage.value != 0) {
                        currentPage.value -= 1
                    }
                })
    ) {}
}