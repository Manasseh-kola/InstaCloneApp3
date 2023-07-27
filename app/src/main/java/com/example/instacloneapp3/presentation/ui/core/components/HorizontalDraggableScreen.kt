package com.example.instacloneapp3.presentation.ui.core.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

/*
Allows Screen to be dragged horizontally (to the right)
 */
@Composable
fun HorizontalDraggableScreen(
    screenStackIndex: Int = 0,
    dragEnabled: Boolean = true,
    navigationViewModel: NavigationViewModel,
    content: @Composable (ColumnScope.() -> Unit),

){

    val navigationUiState = navigationViewModel.navigationState.collectAsState()
    val topScreenXOffset = navigationUiState.value.topScreenXOffset
    val prevScreenXOffset = navigationUiState.value.prevScreenXOffset

    val prevScreenIndex = navigationUiState.value.prevScreenIndex
    val animatedTopXOffset = animateFloatAsState(targetValue = topScreenXOffset, label = "")
    val animatedPrevXOffset = animateFloatAsState(targetValue = prevScreenXOffset, label = "")
    val currentStack = navigationUiState.value.currentStack
    val isTopScreen = screenStackIndex == currentStack.lastIndex && screenStackIndex != prevScreenIndex
    val xBreakPoint = remember { mutableStateOf(0.0f) }
    val breakPointSet = remember { mutableStateOf(false) }


    LaunchedEffect(key1 = currentStack.size){
        if(prevScreenIndex == screenStackIndex){
            delay(500)
            navigationViewModel.resetScreenXOffset()
        }
    }

    DisposableEffect(key1 = Unit){
        onDispose {
            navigationViewModel.cleanUpXOffset()
        }
    }


    Column(
        content = content,
        modifier = Modifier
            .offset {
                if (isTopScreen) {
                    IntOffset(animatedTopXOffset.value.roundToInt(), 0)
                } else {
                    IntOffset(animatedPrevXOffset.value.roundToInt(), 0)
                }
            }
            .onGloballyPositioned { layoutCoordinates ->
                if (!breakPointSet.value) {
                    val rect = layoutCoordinates.boundsInRoot()
                    val maxOffset = rect.topRight.x / 2
                    if (xBreakPoint.value != maxOffset) {
                        xBreakPoint.value = maxOffset
                    }
                    breakPointSet.value = true
                }
            }
            .draggable(
                enabled = (dragEnabled && prevScreenIndex != screenStackIndex),
                orientation = Orientation.Horizontal,
                onDragStopped = {
                    navigationViewModel.horizontalScreenDragEnded(xBreakPoint = xBreakPoint.value)

                },
                state = rememberDraggableState { delta ->
                    navigationViewModel.updateTopScreenXOffset(delta)
                }
            )
    )


}