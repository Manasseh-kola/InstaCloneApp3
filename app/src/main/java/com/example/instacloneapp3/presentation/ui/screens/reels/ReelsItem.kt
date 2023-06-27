package com.example.instacloneapp3.presentation.ui.screens.reels

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.components.VideoPlayer
import com.example.instacloneapp3.presentation.view_models.VideoViewModel


val colorStops = arrayOf(
    0.0f to Color(0,0,0,100),
    0.50f to Color(0,0,0,10),
    1f to Color(0,0,0,120)
)

/*
Reel Item for Vertical Pager
 */
@Composable
fun Reel(
    post: Posts,
    modifier: Modifier,
    navigateToRoute: (String) -> Unit,
    videoViewModal: VideoViewModel,
) {
    val isExpandedText = remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable {
                if (isExpandedText.value) {
                    isExpandedText.value = false
                }
            }
    ) {

        //Dummy Image to enable Preview
//        Image(
//            contentDescription = "",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize(),
//            painter = painterResource(id = PostsRepo().getPosts()[5].profile_picture),
//        )


        //VideoPlayer
        VideoPlayer()

        //Gradient Overlay for visibility of white texts
        Canvas(
            onDraw = {
                val size = size
                drawRect(
                    brush = Brush.verticalGradient(colorStops = colorStops),
                    size = size
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    this.drawContent()
                    drawRect(
                        Color.Black.copy(
                            (if(isExpandedText.value) 0.3F else 0.0F)
                        )
                    )
            }
        )


        //Caption and Reels Owner Information
        CaptionSection(
            profilePictureRes = post.profile_picture,
            userName = post.user_name,
            caption = post.caption,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.BottomStart)
                .padding(10.dp),
            isExpandedText = isExpandedText
        )


        //Side bar for Reels engagement
        val comment = (10..100).random()
        val shares = (10..100).random()
        EngageSideBar(
            soundOwnerPicture = post.profile_picture,
            likes = post.likes,
            comment = comment,
            shares = shares,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 10.dp)
        )

    }
}

