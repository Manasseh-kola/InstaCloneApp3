package com.example.instacloneapp3.presentation.ui.modals

import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

enum class ModalSheets {
    NO_SHEET,
    STORY_MODAL
}

@Composable
fun AppModalSheets(
    currentModalSheet: MutableState<ModalSheets>
){
    when(currentModalSheet.value){
        ModalSheets.NO_SHEET -> {}
        ModalSheets.STORY_MODAL -> {}
    }
}