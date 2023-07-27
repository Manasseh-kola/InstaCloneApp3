package com.example.instacloneapp3.presentation.ui.screens.home_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostItemPager(
    postContent: List<Int>,
    pagerState: PagerState
){
    //--Horizontal Pager to enable multiple posts--
    HorizontalPager(
        pageCount = postContent.size,
        state = pagerState
    ) {page ->
        Image(
            painter = painterResource(id = postContent[page]),
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier
                .size(450.dp)
        )
    }
}