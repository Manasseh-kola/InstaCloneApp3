package com.example.instacloneapp3.presentation.ui.screens.search_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.mock_data.StoriesRepo
import com.example.instacloneapp3.presentation.ui.screens.relationships_screens.user.RelationshipSearchBar
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

/*
Search Screen
 */

@Composable
fun PhotoItem(image:Int){
    Image(
        painter = painterResource(id = image),
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier = Modifier
            .aspectRatio(1f)
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchScreen() {
    val search = remember { mutableStateOf("") }
    val list = PostsRepo().getPosts()
    val posts = list + list + list

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 128.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalItemSpacing = 1.dp
    ){
        item(
            span = StaggeredGridItemSpan.FullLine
        ){
           Row(horizontalArrangement = Arrangement.Center) {
                RelationshipSearchBar(
                    width = 0.94f,
                    input = search,
                    placeholder = "Search",
                    verticalPadding = 5.dp,
                )
            }

        }

        items(posts){ post ->
            PhotoItem(image = post.imageRes)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    InstaCloneApp3Theme() {
        SearchScreen()
    }
}