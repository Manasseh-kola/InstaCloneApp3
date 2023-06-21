package com.example.instacloneapp3.presentation.ui.screens.comments_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CommentInput(
    modifier: Modifier,
) {
    Row(
        modifier = modifier
    ){

        //State of entered Text in Text field
        var textValue = remember { mutableStateOf("") }

        //Text Input
        BasicTextField(
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
            value = textValue.value,
            onValueChange = { textValue.value = it },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .border(
                            shape = RoundedCornerShape(20.dp),
                            color = Color.LightGray,
                            width = 1.dp
                        )
                        .padding(10.dp)
                ) {


                    //Place Holder Text
                    if (textValue.value.isEmpty()) {
                        Text(
                            "Add a comment...",
                            color = Color.LightGray,
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                    }

                    //Text Field
                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth()

                    ){
                        innerTextField()
                    }


                    //Trailing Icons
                    if (textValue.value == ""){
                        Row(modifier = Modifier.align(Alignment.TopEnd)) {
                            Icon(imageVector = Icons.Outlined.MailOutline, contentDescription = "")
                        }
                    }else{
                        Text(
                            text = "post",
                            color = Color.LightGray,
                            modifier = Modifier.align(Alignment.TopEnd))
                    }

                }
            },
        )
    }
}