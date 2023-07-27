package com.example.instacloneapp3.presentation.ui.screens.favourites_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import kotlin.math.roundToInt

@Composable
fun FavouritesScreen(){
    val posts = listOf<Int>()
    val offsetX = remember { mutableStateOf(0f) }
    val animatedOffsetX = animateFloatAsState(offsetX.value)
    Column(
        modifier = Modifier
            .offset { IntOffset(animatedOffsetX.value.roundToInt(), 0) }
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        val xBreakPoint = 750.4563f
                        if (offsetX.value >= xBreakPoint) {
                            //Navigate back
                            TODO()
                        } else if (offsetX.value != 0.0f) {
                            offsetX.value = 0.0f
                        }
                    },
                ) { change, dragAmount ->
                    change.consume()
                    if (dragAmount.x + offsetX.value > 0.0f) {
                        offsetX.value += dragAmount.x
                    }
                }
            }
    ){
        FavouritesScreenHeader(modifier = Modifier){}
        Divider()
        if(posts.isEmpty()) ListNotCreated()
        else FavouritesList()
    }
}

@Composable
fun ListNotCreated() {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "",
                modifier = Modifier.size(70.dp)
            )

            Text(
                text = "Choose the account you can't miss out on",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                lineHeight = 30.sp,
                fontSize = 30.sp
            )

            Text(
                text = "Add accounts your favourites to see their posts here, starting with the most recent posts",
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = "Add Favourites",
                color = Color(3, 109, 202, 201),
                modifier = Modifier.padding(top = 20.dp)
            )
        }

        Spacer(Modifier.height(80.dp))
    }
}

@Composable
fun FavouritesList() {
    LazyColumn {}
}

@Composable
fun FavouritesScreenHeader(
    modifier: Modifier,
    hideModal: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        hideModal()
                    }
            )
            Text(
                text = "Favourites",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "",
            modifier = Modifier.size(30.dp)
        )

    }
}

@Composable
@Preview(showBackground = true)
fun FavouritesScreenPreview(){
    InstaCloneApp3Theme {
        FavouritesScreen()
    }
}