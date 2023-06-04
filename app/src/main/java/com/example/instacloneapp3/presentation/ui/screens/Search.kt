package com.example.instacloneapp3.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.StoriesRepo
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


@Composable
fun PhotoItem(image:Int){
    Image(
        contentScale = ContentScale.Crop,
        painter = painterResource(id = image),
        contentDescription = "",
        modifier = Modifier
            .size(250.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var text_value = remember { mutableStateOf("") }
    val posts = StoriesRepo().getStories()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ){
        item(
            span = { GridItemSpan(maxLineSpan) }
        ){
            Row(
                horizontalArrangement = Arrangement.Center
            ){

                BasicTextField(
                    value = text_value.value,
                    onValueChange = {text_value.value = it},
                    decorationBox = {innerTextField ->
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(0.88f)
                                .border(
                                    shape = RoundedCornerShape(15.dp),
                                    color = Color.Black,
                                    width = 1.dp
                                )
                                .padding(10.dp)
                        ){
                            //Leading Icons
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "",
                            )

                            //Place Holder Text
                            if (text_value.value.isEmpty()) {
                                Text(
                                    "Search",
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(start = 30.dp)
                                )
                            }

                            //Text Field
                            Row(
                                modifier = Modifier
                                    .padding(start = 30.dp)
                                    .fillMaxWidth()

                            ){
                                innerTextField()
                            }
                        }
                    }
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