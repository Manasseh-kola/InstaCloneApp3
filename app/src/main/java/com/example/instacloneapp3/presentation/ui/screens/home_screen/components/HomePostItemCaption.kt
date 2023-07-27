package com.example.instacloneapp3.presentation.ui.screens.home_screen.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.instacloneapp3.presentation.ui.screens.reels.components.loremText
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme

@Composable
fun UserCaption(username: String, caption: String){

    val maxLines = remember{ mutableStateOf(2)}
    val animatedMaxLines = animateIntAsState(
        targetValue = maxLines.value,
        animationSpec = spring(
            dampingRatio = 0.6F,
            stiffness = Spring.StiffnessMedium
        )
    )
    val isTextOverflow = remember{ mutableStateOf(false)}
    val isExpandedText = remember{ mutableStateOf(false)}
    Column(){
        Row {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(username)
                    }
                    append(" $loremText")

                },
                overflow = TextOverflow.Ellipsis,
                maxLines = animatedMaxLines.value,
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.hasVisualOverflow) {

                        if (!isTextOverflow.value) {
                            isTextOverflow.value = true
                        }
                    }
                }
            )
        }

        if(!isExpandedText.value){
            Text(
                color = Color(100,100,100),
                text = "more",
                modifier = Modifier
                    .clickable {
                        maxLines.value = 30
                        isExpandedText.value = true
                    }
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun UserCaptionPreview(){
    InstaCloneApp3Theme{
        UserCaption(
            caption = loremText,
            username = "David_adesanya",
        )
    }
}