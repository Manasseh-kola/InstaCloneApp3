package com.example.instacloneapp3.presentation.mock_data

data class Posts (
    val id: Int,
    val likes: Int,
    val imageRes : Int,
    val caption : String,
    val user_name: String,
    val profile_picture: Int,
    val mediaContent: List<Int> = mutableListOf<Int>()
)