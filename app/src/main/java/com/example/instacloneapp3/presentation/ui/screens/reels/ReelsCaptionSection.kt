package com.example.instacloneapp3.presentation.ui.screens.reels

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.ui.screens.home_screen.LikedBy
import com.example.instacloneapp3.presentation.ui.screens.home_screen.user
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


const val loremText = "The @Preview annotation lets you preview your composable" +
        " functions within Android Studio without having to build and" +
        " install the app to an Android device or emulator." +
        " The annotation must be used on a composable function that does" +
        " not take in parameters. For this reason, you can't preview the" +
        " MessageCard function directly. Instead, make a second function" +
        " named PreviewMessageCard, which calls MessageCard with an appropriate" +
        " parameter. Add the @Preview annotation before @Composable."

@Composable
fun CaptionSection(
    userName: String,
    profilePictureRes: Int,
    caption: String,
    modifier: Modifier,
    isExpandedText: MutableState<Boolean>,
){


    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier =  Modifier.padding(vertical = 8.dp)
        ) {

            //Reel Owner Profile picture
            Image(
                painter = painterResource(id = profilePictureRes),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)

            )

            //Reel Owner User name
            Text(
                text ="$userName",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 10.dp),
            )

            //Follow button if Reel Owner is not in Following list
            Text("Follow",
                color = Color.White,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 3.dp)
                    .clickable {}
            )
        }

        //User Caption
        AnimatedVisibility(
            enter = expandVertically(
                animationSpec = spring(
                    dampingRatio = 0.6F,
                    stiffness = Spring.StiffnessMedium
                )
            ),
            exit = shrinkVertically(
                animationSpec = spring(
                    dampingRatio = 0.6F,
                    stiffness = Spring.StiffnessMedium
                )
            ),
            visible = isExpandedText.value
        ) {

            Text(
//                maxLines = if (isExpandedText.value) Int.MAX_VALUE else 1,
                text = loremText,
                color = Color.White,
                modifier = Modifier
                    .fillMaxHeight(0.45f)
                    .verticalScroll(state = rememberScrollState())
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = { isExpandedText.value = true }
                    )
            )

        }

        if (!isExpandedText.value) {
            Text(
                maxLines = if (isExpandedText.value) Int.MAX_VALUE else 1,
                text = loremText,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                modifier = Modifier
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = { isExpandedText.value = true }
                    )
            )
        }



        //Mutual Friends that have liked or commented on Reel
        Row(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            LikedBy(
                imageSize = 22.dp,
                imageSpacing = 28.dp,
                borderColor = Color.Gray
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = "liked by ${user.user_name} and 1,000 others",
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
            )
        }

        SoundOwner( soundOwner = userName)
    }
}


@Composable
@Preview(showBackground = true)
fun CaptionSectionPreview(@PreviewParameter(LoremIpsum::class) text: String){
    InstaCloneApp3Theme() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
        ){
            CaptionSection(
                profilePictureRes = user.profile_picture,
                userName = user.user_name,
                caption = user.bio,
                modifier = Modifier,
                isExpandedText = remember{mutableStateOf(false)},
            )
        }
    }
}