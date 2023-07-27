package com.example.instacloneapp3.presentation.ui.screens.profile_screen.content

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme

@Composable
fun TaggedContent(
    gridState: LazyGridState,
    scrollState: ScrollState,
    showModal: MutableState<Boolean>,
    modalStartScrollIndex: MutableState<Int>,
    transformOriginOffset: MutableState<Offset>
){

    val posts = PostsRepo().getPosts()

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(1.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        userScrollEnabled = scrollState.value == scrollState.maxValue,
    ){
        itemsIndexed(posts){ index, post ->
            Column {
                Box{
                    PostItem(
                        index = index,
                        showModal = showModal,
                        thumbnailImage = post.mediaContent[0],
                        modalStartScrollIndex = modalStartScrollIndex,
                        transformOriginOffset = transformOriginOffset,
                    )

                    if(post.mediaContent.size > 1){
                        MultipleContentIndicator(
                            Modifier
                                .align(Alignment.TopEnd)
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TaggedContentPreview(){
    InstaCloneApp3Theme {
        TaggedContent(
            rememberLazyGridState(),
            rememberScrollState(),
            remember{ mutableStateOf(false) },
            remember{ mutableStateOf(0)},
            remember{ mutableStateOf(Offset(0f,0f))}
        )
    }
}