package com.example.instacloneapp3.presentation.ui.screens.reels.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme

/*
Header for Reels Screen
*/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelsHeader(pagerState: PagerState) {
    val text = if (pagerState.currentPage == 0) stringResource(R.string.reels)
    else ""

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Text(
            text = text,
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )

        Icon(
            tint = Color.White,
            contentDescription = "",
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Filled.PlayArrow,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun ReelsHeaderPreview(){
    InstaCloneApp3Theme() {
        Row(
            modifier = Modifier.background(Color.Black)
        ){
            ReelsHeader(pagerState = rememberPagerState())
        }
    }
}