package com.example.instacloneapp3.presentation.ui.screens.profile_screen.content

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.screens.profile_screen.PostItem
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

@Composable
fun ReelsContent(
    gridState: LazyGridState,
    scrollState: ScrollState,
    showModal: MutableState<Boolean>,
    modalStartScrollIndex: MutableState<Int>,
    transformOriginOffset: MutableState<Offset>
){
    var index = 0
    val posts = PostsRepo().getPosts()

    LazyVerticalGrid(
        state = gridState,
        userScrollEnabled = scrollState.value == 1023,
        columns = GridCells.Adaptive(minSize = 128.dp)
    ){
        items(posts){ post ->
            PostItem(post.imageRes, index, showModal, modalStartScrollIndex, transformOriginOffset)
            index ++
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ReelContentPreview(){
    InstaCloneApp3Theme {
        ReelsContent(
            rememberLazyGridState(),
            rememberScrollState(),
            remember{ mutableStateOf(false)},
            remember{ mutableStateOf(0)},
            remember{ mutableStateOf(Offset(0f,0f))}
        )
    }
}