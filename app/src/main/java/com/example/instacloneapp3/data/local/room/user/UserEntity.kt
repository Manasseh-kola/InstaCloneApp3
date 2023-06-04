package com.example.instacloneapp3.data.local.room.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.instacloneapp3.data.network.models.User


@Entity(tableName = "user_")
data class UserEntity (
    @PrimaryKey
    var email : String,
    var name : String ,
    var user_name: String,
    var followers_count: Int,
    var following_count: Int,
    var bio : String,
    var profile_picture : Int,
){
    fun toRemoteUser() : User{
        return User(
            email = email,
            name = name,
            user_name = user_name,
            followers_count = followers_count,
            following_count = following_count,
            bio = bio,
            profile_picture = profile_picture,
        )
    }
}