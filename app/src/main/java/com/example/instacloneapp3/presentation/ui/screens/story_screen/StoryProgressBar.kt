package com.example.instacloneapp3.presentation.ui.screens.story_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun TimeBar(
    modifier: Modifier,
    startAnimation: Boolean = false,
    leftTimeBar: Boolean = false,
    playAnimation: MutableState<Boolean>,
    offsetY: MutableState<Float>,
    onAnimationEnd: ()->Unit
){
    //State of progress on progress indicator bar
    val progress = remember { mutableStateOf(0.00f)}

    //AnimatedProgress is used to achieve smooth animation when the progress increases
    val animatedProgress = animateFloatAsState(targetValue = progress.value,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec)

    /*
    Play Animation of Progress bar aligning with the current index of stories
     */
    if (startAnimation){

        //Pause Animation if the keyboard is visible (PlayAnimation is false when the keyboard is visible
        if (playAnimation.value && offsetY.value == 0.0f) {
            LaunchedEffect(key1 = Unit) {
                while (progress.value < 1f) {
                    progress.value += 0.01f
                    delay(50)
                }
                onAnimationEnd()
            }
        }
    }
    else{
        if (progress.value != 0.0f) { progress.value = 0.0f }
    }

    /*
    Set progress to 1 if the indicator bar is the the left of the current index in stories else 0
     */
    var nonAnimatedProgress = if (leftTimeBar) 1f else 0.0f
    var indicatorProgress = if (startAnimation) animatedProgress.value
    else nonAnimatedProgress

    LinearProgressIndicator(
        color = Color.White,
        trackColor = Color.LightGray,
        modifier = modifier
            .padding(vertical = 12.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(12.dp)),
        progress = indicatorProgress
    )
}