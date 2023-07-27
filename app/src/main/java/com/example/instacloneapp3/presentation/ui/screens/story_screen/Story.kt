package com.example.instacloneapp3.presentation.ui.screens.story_screen

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.ui.screens.story_screen.components.NextStoryButton
import com.example.instacloneapp3.presentation.ui.screens.story_screen.components.PrevStoryButton
import com.example.instacloneapp3.presentation.ui.screens.story_screen.components.ReplyStory
import com.example.instacloneapp3.presentation.ui.screens.story_screen.components.StoryHeader
import com.example.instacloneapp3.presentation.ui.screens.story_screen.components.StoryQuickReply
import com.example.instacloneapp3.presentation.ui.screens.story_screen.components.TimeBar
import kotlin.math.roundToInt

@Composable
fun StoryScreen(
    nextStory: () -> Unit
){

    //--List of Stories to be displayed--
    val stories = PostsRepo().getPosts().take(3)

    //--Current index of stories--
    val currentPage = remember{ mutableStateOf(0 )}

    /*
    playAnimation state is used to control when the animation of a story progress bar is played
    playAnimation state is set to false when the keyboard is visible( send message text field is clicked)
     */
    val playAnimation = remember { mutableStateOf(true)}

    //--Animate Drag state--
    val offsetY = remember { mutableStateOf(0f) }
    val animatedOffsetY = animateFloatAsState(targetValue = offsetY.value)

    //--Screen is dragged--
    val dragged = remember{ mutableStateOf(false)}


    Column(
        modifier = Modifier
            .offset {
                IntOffset(
                    0,
                    animatedOffsetY.value.roundToInt()
                )
            }
            .fillMaxSize()
//            .pointerInput(Unit) {
//                detectDragGestures(
//                    onDragEnd = {
//
//                        val yBreakPoint = 283.63824f
//                        if (offsetY.value >= yBreakPoint) {
//                            hideModal()
//                        } else if (offsetY.value != 0.0f) {
//                            offsetY.value = 0.0f
//                        }
//                        dragged.value = true
//                        playAnimation.value = true
//                    },
//                    onDragStart = {
//                        if (!dragged.value) {
//                            dragged.value = true
//                        }
//                        if (playAnimation.value) {
//                            playAnimation.value = false
//                        }
//                    }
//                ) { change, dragAmount ->
//                    change.consume()
//                    if (offsetY.value + dragAmount.y >= 0) {
//                        offsetY.value += dragAmount.y
//                    }
//
//                }
//            }
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = if (playAnimation.value) Color.Black
                    else Color.Transparent
                )
                .onGloballyPositioned { layoutCoordinates ->
                    val size = layoutCoordinates.size
                    Log.i("sizes", "${size.height}")

                    //--Set playAnimation state to false if keyboard is visible--
                    if (size.height == 1275) {
                        if (playAnimation.value) playAnimation.value = false
                    } else if (!playAnimation.value) playAnimation.value = true
                }

        ) {

            //--Story Content--
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = stories[currentPage.value].profile_picture),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
            )

            //--Light Shading over screen to make white fonts more readable--
            Canvas(
                modifier = Modifier.fillMaxSize(),
                onDraw = {
                    val size = size
                    drawRect(
                        color = Color.hsv(
                            0F,
                            0F,
                            0F,
                            if(playAnimation.value) 0.2F else 0.7F),
                        size = size
                    )
                }
            )

            //--Progress bars for story--
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                for (i in stories.indices) {
                    TimeBar(
                        modifier = Modifier.weight(1F),
                        i == currentPage.value,
                        (i < currentPage.value),
                        playAnimation,
                        offsetY
                    ) {
                        //--Call back function after time(5 seconds) expires--
                        if (currentPage.value < stories.size - 1) {
                            currentPage.value += 1
                        } else nextStory()
                    }
                }

            }


            //--Show next Story--
            NextStoryButton(
                stories.size,
                currentPage,
                !playAnimation.value,
                modifier = Modifier.align(Alignment.BottomEnd)
            )

            //--Show Previous Story--
            PrevStoryButton(
                currentPage,
                !playAnimation.value,
                modifier = Modifier.align(Alignment.BottomStart)
            )

            //--Story Information and Story owner Profile Information--
            StoryHeader()

            //--Reply Story and share story--
            ReplyStory(modifier = Modifier.align(Alignment.BottomStart))

            androidx.compose.animation.AnimatedVisibility(
                visible = !playAnimation.value && !dragged.value,
                enter = expandVertically(expandFrom = Alignment.Bottom),
                exit = shrinkVertically()
            ) {
                StoryQuickReply()
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun StoryScreenPreview(){
    InstaCloneApp3Theme {
        StoryScreen{}
    }
}