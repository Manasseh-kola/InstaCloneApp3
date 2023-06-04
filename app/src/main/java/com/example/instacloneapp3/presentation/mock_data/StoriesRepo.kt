package com.example.instacloneapp3.presentation.mock_data

import com.example.instacloneapp3.R

class StoriesRepo {
    private val stories : List<Stories> = listOf(
        Stories(1, R.drawable.story1),
        Stories(2, R.drawable.story2),
        Stories(3, R.drawable.story3),
        Stories(4, R.drawable.story4),
        Stories(5, R.drawable.story5),
        Stories(6, R.drawable.story6),
        Stories(7, R.drawable.story7)
    )

    fun getStories(): List<Stories> {
        return stories
    }
}