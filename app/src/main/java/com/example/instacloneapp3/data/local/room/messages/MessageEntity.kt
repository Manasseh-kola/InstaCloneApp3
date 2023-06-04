package com.example.instacloneapp3.data.local.room.messages

import androidx.room.Entity

@Entity(tableName = "messages")
data class MessageEntity(
    var message : String
)
