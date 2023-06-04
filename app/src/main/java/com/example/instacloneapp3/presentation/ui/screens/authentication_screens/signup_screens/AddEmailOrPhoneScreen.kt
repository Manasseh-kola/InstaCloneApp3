package com.example.instacloneapp3.presentation.ui.screens.authentication_screens.signup_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instacloneapp3.presentation.ui.components.FormButton
import com.example.instacloneapp3.presentation.ui.components.FormField
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


@Composable
fun ContactSelector(width: Float, height: Dp, color: Color, modifier: Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth(width)
            .height(height)
            .background(color)
    ){}
}

@Composable
fun AddContactInfoScreen(){
    val email = remember{ mutableStateOf("")}
    val phoneNumber = remember{ mutableStateOf("")}
    val contactType = remember{ mutableStateOf("phone")}

    val selectedAlignment = if (contactType.value == "phone") Alignment.CenterStart
    else Alignment.CenterEnd

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ){
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clickable(onClick = {/*TODO*/ })
            )
        }

        Text(
            "Add phone number or email address",
            lineHeight = 32.sp,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 20.dp)
                .fillMaxWidth(0.75f)
        )
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
        ){
            Text(
                "Phone",
                fontSize = 23.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = { contactType.value = "phone" })
            )

            Text(
                "Email",
                fontSize = 23.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = { contactType.value = "email" })
            )
        }
        Box(
            modifier = Modifier
                .padding(bottom = 10.dp)
        ){

            //Base Selected Indicator bar
            ContactSelector(
                0.9f, 1.dp, Color.Gray,
                Modifier.align(Alignment.Center),
            )

            //Selected Contact type
            ContactSelector(
                0.45f, 1.5.dp, Color.Black,
                Modifier.align(selectedAlignment),
            )
        }


        if (contactType.value == "phone") {
            FormField(input = email, placeholder = "Phone number", width = 0.9f)
        }else{
            FormField(input = email, placeholder = "Email address", width = 0.9f)
        }

        FormButton(text = "Next", width = 0.9f, textSize = 16.sp)

        Text(
            text = "You may receive SMS notifications from us for security and login purposes",
            maxLines = 2,
            fontSize = 12.sp,
            lineHeight = 15.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp).fillMaxWidth(0.78f)
        )

    }
}


@Composable
@Preview(showBackground = true)
fun AddContactInfoScreenPreview(){
    InstaCloneApp3Theme() {
        AddContactInfoScreen()
    }
}