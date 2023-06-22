package com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_screen_bottom_sheets.user.try_new_account

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme

enum class ModalPages{
    SmallerGroup,
    ExploreInterests
}

val modalPages = listOf(
    ModalPages.SmallerGroup,
    ModalPages.ExploreInterests
)

@Composable
fun PagerIndicator(color: Color = Color.Gray, indicatorSize: Dp = 4.dp){
    Canvas(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .size(indicatorSize),
        onDraw = {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawCircle(
                color = color,
                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                radius = size.minDimension,
            )
        })
}

@Composable
fun TryNewAccountButton(
    text:String,
    width:Float = 0.9f,
    textSize: TextUnit = 14.sp
){
    val blueColor  = Color(55, 151, 239, 255)
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(blueColor),
        modifier = Modifier
            .fillMaxWidth(width)
            .padding(vertical = 20.dp),
        shape = RoundedCornerShape(8)

    ) {
        Text(
            "$text",
            color = Color.White,
            fontSize = textSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 10.dp
            )
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TryNewAccount(){

    val pagerState = rememberPagerState()
    val blueColor  = Color(55, 151, 239, 255)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(){
            HorizontalPager(pageCount = modalPages.size) { page ->
                when(modalPages[page]){
                    ModalPages.SmallerGroup -> CreateSmallerGroup()
                    ModalPages.ExploreInterests -> ExploreInterests()
                }
            }
        }

        Row(
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            for(page in 0..1){
                val selectedColor = if(page == pagerState.currentPage) Color(55, 151, 239, 255)
                else Color.Gray
                PagerIndicator(selectedColor)
            }
        }

        Divider()

        Text(
            text = "Add account",
            fontSize = 13.sp, color = blueColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp, bottom = 40.dp)
        )
    }

}

@Composable
@Preview(showBackground = true)
fun TryNewAccountPreview(){
    InstaCloneApp3Theme {
        TryNewAccount()
    }
}