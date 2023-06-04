package com.example.instacloneapp3.presentation.ui.screens.direct_message_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DmInput(
    modifier: Modifier,
    keyboardVisibility: MutableState<Boolean>
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
                            color = Color.Black,
                            width = 1.dp
                        )
                        .padding(10.dp)
                ) {

                    //Leading Icons
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "" )

                    //Place Holder Text
                    if (textValue.value.isEmpty()) {
                        Text(
                            "Message...",
                            color = Color.Black,
                            modifier = Modifier
                                .padding(start = 30.dp)
                        )
                    }

                    //Text Field
                    Row(
                        modifier = Modifier
                            .padding(start = 30.dp)
                            .fillMaxWidth()

                    ){
                        innerTextField()
                    }


                    //Trailing Icons
                    if (!keyboardVisibility.value){
                        Row(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .width(110.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(imageVector = Icons.Outlined.Phone, contentDescription = "")
                            Icon(imageVector = Icons.Outlined.Info, contentDescription = "")
                            Icon(imageVector = Icons.Outlined.MailOutline, contentDescription = "")
                            Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "")
                        }
                    }

                }
            },
        )
    }
}