package com.example.instacloneapp3.presentation.ui.screens.home_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.mock_data.Posts
import com.example.instacloneapp3.presentation.mock_data.PostsRepo
import com.example.instacloneapp3.presentation.ui.modals.ModalSheets
import com.example.instacloneapp3.presentation.ui.rememberAppState
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme


/*
Story Item for Stories Lazy List
 */

@Composable
fun StoryItem(
    story: Posts,
    navigateToRoute: (String) -> Unit,
    currentModalSheet: MutableState<ModalSheets>,
    showModal: MutableState<Boolean>,
    isStoryWatched: Boolean = false,
    storyName: String = "story name",
) {

    //Root Composable
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable {
                    currentModalSheet.value = ModalSheets.STORY_MODAL
                    showModal.value = true
                }
        ) {

            //Thumbnail for Story
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = story.imageRes),
                contentDescription = "",
                modifier = storyImageModifier
                    .border(5.dp, Color.White, CircleShape)
            )


            //Show Circle if story has not been watched
            if (!isStoryWatched) {
                Canvas(
                    modifier = Modifier.size(90.dp),
                    onDraw = {
                        val canvasWidth = size.width
                        val canvasHeight = size.height

                        drawCircle(
                            brush = Brush.linearGradient(
                                colorStops = colorStops,
                                start = Offset(100f, 0.0f),
                                end = Offset(0.0f, 100.0f)
                            ),
                            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                            radius = size.minDimension / 2,
                            style = Stroke(8.6F),
                        )
                    }
                )
            }
        }

        //Story username/name
        Text(
            maxLines = 1,
            text = storyName,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(100.dp)
        )
    }

}


@Composable
@Preview(showBackground = true)
fun StoryItemPreview(){
    val appState = rememberAppState()
    InstaCloneApp3Theme() {
        StoryItem(
            story = PostsRepo().getPosts()[0],
            navigateToRoute = appState::onNavigateToScreen,
            showModal = remember { mutableStateOf(false)},
            currentModalSheet = remember{ mutableStateOf(ModalSheets.NO_SHEET)},
        )
    }
}