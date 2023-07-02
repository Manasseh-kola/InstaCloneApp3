package com.example.instacloneapp3.presentation.ui.screens.home_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.ui.bottom_sheets.BottomSheets
import com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_screen_bottom_sheets.user.try_new_account.PagerIndicator
import com.example.instacloneapp3.presentation.ui.navigation.graphs.AppScreens
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

/*
Home Post Footer
Contains
- Like, Comment, Share, and Bookmark Buttons
- Pager Indicator for Posted content Pager
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostFooter(
    navigationViewModel: NavigationViewModel,
    pagerState: PagerState,
    pageCount: Int = 0
) {

    //Root Composable
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
    ){

        //Like, Comment, and Share buttons
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(100.dp),


        ) {
            //Like Button
            Icon(
                painter = painterResource(id = R.drawable.ic_outlined_favorite),
                contentDescription ="",
                modifier = Modifier
                    .size(25.dp)
            )

            //Comment Button
            Icon(
                painter = painterResource(id = R.drawable.ic_outlined_comment),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
            )

            //Share Button
            Icon(
                painter = painterResource(id = R.drawable.ic_dm),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        navigationViewModel.openBottomSheet(
                            currentBottomSheet = BottomSheets.SHARE_CONTENT,
                            currentScreen = AppScreens.Home,
                        )
                    }
            )
        }

        //pager Indicator
        if(pageCount > 1){
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                for(page in 0 until pageCount){
                    val selectedColor = if(page == pagerState.currentPage) Color(55, 151, 239, 255)
                    else Color.Gray
                    PagerIndicator(selectedColor)
                }
            }

        }

        //Bookmark Button
        Icon(
            painter = painterResource(id = R.drawable.bookmark_outline),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(25.dp)
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun PostFooterPreview(){
    InstaCloneApp3Theme {
        PostFooter(
            pagerState = rememberPagerState(),
            navigationViewModel = hiltViewModel(),
        )
    }
}