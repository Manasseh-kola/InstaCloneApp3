package com.example.instacloneapp3.data.network.data_transfer_objects

import com.example.instacloneapp3.data.local.room.user.UserEntity
import com.example.instacloneapp3.data.network.models.User

data class UserDto (
    var email : String,
    var name : String ,
    var user_name: String,
    var followers_count: Int,
    var following_count: Int,
    var bio : String,
    var profile_picture : Int,
)

fun UserDto.toUser(): User {
    return User(
        email = email,
        name = name,
        user_name = user_name,
        followers_count = followers_count,
        following_count = following_count,
        bio = bio,
        profile_picture = profile_picture
    )
}

fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        email = email,
        name = name,
        user_name = user_name,
        followers_count = followers_count,
        following_count = following_count,
        bio = bio,
        profile_picture = profile_picture
    )
}