package com.example.instacloneapp3.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FormField(
    input: MutableState<String>,
    placeholder: String,
    leadingIcon: ImageVector? = null,
    width: Float = 1f
){
    BasicTextField(
        singleLine = true,
        value = input.value,
        onValueChange = {input.value = it},
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(width)
                    .background(Color(245, 245, 245))
                    .border(
                        shape = RoundedCornerShape(5.dp),
                        color = Color.Gray, width = 1.dp
                    )
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            ) {

                //Place Holder Text
                if (input.value.isEmpty()){
                    Text(
                        "$placeholder",
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(start = 7.dp)
                    )
                }

                //Text Field
                Row(
                    modifier = Modifier
                        .padding(start = 7.dp)
                        .fillMaxWidth()
                ){
                    innerTextField()
                }


                //Leading Icon
                if (leadingIcon != null){
                    Icon(
                        imageVector = leadingIcon ,
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }

            }
        }
    )
}