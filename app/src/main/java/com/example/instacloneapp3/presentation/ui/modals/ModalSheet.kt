package com.example.instacloneapp3.presentation.ui.modals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.instacloneapp3.presentation.ui.screens.comments_screen.CommentScreen

enum class ModalSheets {
    NO_SHEET,
    STORY_MODAL,
    COMMENT_MODAL,
    MESSAGES
}

@Composable
fun AppModalSheets(
    currentModalSheet: MutableState<ModalSheets>,
    modalState: MutableState<Boolean>
){
    when(currentModalSheet.value){
        ModalSheets.NO_SHEET -> {}
        ModalSheets.STORY_MODAL -> {}
//        ModalSheets.COMMENT_MODAL -> { CommentScreen(
//            showModal = modalState,
//            kFunction0 = appState::hideModal
//        )}
        else -> {}
    }
}