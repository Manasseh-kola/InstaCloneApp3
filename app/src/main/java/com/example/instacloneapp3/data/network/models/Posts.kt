package com.example.instacloneapp3.data.network.models

data class Posts(
    val id: Int,
    val likes: Int,
    val imageRes : Int,
    val caption : String,
    val user_name: String,
    val profile_picture: Int,
)
