package com.example.instacloneapp3.data.network.models

data class User(
    var email : String,
    var name : String ,
    var user_name: String,
    var followers_count: Int,
    var following_count: Int,
    var bio : String,
    var profile_picture : Int,
)
