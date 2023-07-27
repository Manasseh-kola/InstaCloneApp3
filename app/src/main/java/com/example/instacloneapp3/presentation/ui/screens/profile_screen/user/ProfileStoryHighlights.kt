package com.example.instacloneapp3.presentation.ui.screens.profile_screen.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.mock_data.StoriesRepo
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme


/*
User Story Highlights DropDown
 */

@Composable
fun StoryHighlightsDropDown(){

    val stories = StoriesRepo().getStories()
    val expand = remember { mutableStateOf(false) }

    //Drop Down Icon
    val icon = if (expand.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = { expand.value = !expand.value }
                ),
            ) {

            //StoryHighlights Text
            Text(
                text = stringResource(R.string.story_highlights),
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.size(5.dp))

            //Drop-Down Arrow
            Icon(
                imageVector = icon,
                contentDescription = stringResource(R.string.drop_down_arrow)
            )

        }

        //Shop Story Highlights when Dropdown is active
        if (expand.value) { StoryHighlightList(stories) }
        else Divider()

    }
}

@Composable
@Preview(showBackground = true)
fun StoryHighlightsDropDownPreview(){
    InstaCloneApp3Theme {
        StoryHighlightsDropDown()
    }
}