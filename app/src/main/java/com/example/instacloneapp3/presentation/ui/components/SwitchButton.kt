package com.example.instacloneapp3.presentation.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


@Composable
fun SwitchButton(buttonSize: Int = 20){

    //Animated Color
    val buttonColor = remember{mutableStateOf(Color.LightGray)}
    val animatedButtonColor = animateColorAsState(targetValue = buttonColor.value )

    //Animated Offset
    val xOffset = remember{ mutableStateOf(0.dp)}
    val animatedXOffset = animateDpAsState(
        targetValue = xOffset.value,
        animationSpec = spring(
            dampingRatio = 0.65F,
            stiffness = Spring.StiffnessMedium
        )
    )

    Box(
        modifier = Modifier
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {
                    when (buttonColor.value) {
                        Color.LightGray -> {
                            xOffset.value = buttonSize.dp
                            buttonColor.value = Color(3, 169, 244)
                        }

                        else -> {
                            xOffset.value = 0.dp
                            buttonColor.value = Color.LightGray
                        }
                    }
                }
            )
    ){
        Row(
            modifier = Modifier
                .width(buttonSize.dp * 1.8f)
                .height(buttonSize.dp)
                .clip(RoundedCornerShape(buttonSize.dp / 2))
                .background(animatedButtonColor.value)
        ){}
        Row(
            modifier = Modifier
                .offset(x = animatedXOffset.value)
                .clip(CircleShape)
                .background(Color.White)
                .border(buttonSize.dp / 20, animatedButtonColor.value, CircleShape,)
                .size(buttonSize.dp)

        ){}
    }
}

@Composable
@Preview(showBackground = true)
fun SwitchButtonPreview(){
    InstaCloneApp3Theme {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            SwitchButton(100)
        }
    }
}