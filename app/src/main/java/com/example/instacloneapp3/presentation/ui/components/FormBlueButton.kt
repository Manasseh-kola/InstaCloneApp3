package com.example.instacloneapp3.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormButton(
    text:String,
    width:Float = 0.95f,
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