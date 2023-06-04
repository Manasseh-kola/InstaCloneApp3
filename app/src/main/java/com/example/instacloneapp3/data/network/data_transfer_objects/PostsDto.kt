package com.example.instacloneapp3.data.network.data_transfer_objects

import com.example.instacloneapp3.data.network.models.Posts

data class PostsDto(
    val id: Int,
    val likes: Int,
    val imageRes : Int,
    val caption : String,
    val user_name: String,
    val profile_picture: Int,
)

fun PostsDto.toPosts(): Posts {
    return Posts(
        id = id,
        likes = likes,
        imageRes = imageRes,
        caption = caption,
        user_name = user_name,
        profile_picture = profile_picture
    )
}
