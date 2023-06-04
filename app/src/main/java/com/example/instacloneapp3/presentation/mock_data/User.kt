package com.example.instacloneapp3.presentation.mock_data

import com.example.instacloneapp3.R

data class User (
    var name : String = "David",
    var user_name: String = "David",
    var followers_count: Int = 100,
    var following_count: Int = 10,
    var bio : String = "Software Engineer",
    var profile_picture : Int = R.drawable.user
)