package com.example.instacloneapp3.presentation.mock_data

class MessagesRepo {
    private val messages = listOf<Messages>(
        Messages(
            sent = true,
            message = "Yo, whats popping my G!",
            time = "16:22",
            imageRes = null
        ),

        Messages(
            sent = true,
            message = "Yo, whats popping my G!",
            time = "16:21",
            imageRes = null
        ),

        Messages(
            sent = true,
            message = "Yo, whats popping my G!",
            time = "16:20",
            imageRes = null
        ),

        Messages(
            sent = false,
            message = "Yo, whats popping my G!",
            time = "16:19",
            imageRes = null
        ),

        Messages(
            sent = true,
            message = "Yo, whats popping my G!",
            time = "16:18",
            imageRes = null
        ),

        Messages(
            sent = false,
            message = "Yo, whats popping my G!",
            time = "16:17",
            imageRes = null
        ),

        Messages(
            sent = false,
            message = "Yo, whats popping my G!",
            time = "16:16",
            imageRes = null
        ),

        Messages(
            sent = false,
            message = "Yo, whats popping my G!",
            time = "16:15",
            imageRes = null
        ),

        Messages(
            sent = true,
            message = "Yo, whats popping my G!",
            time = "16:14",
            imageRes = null
        ),

        Messages(
            sent = false,
            message = "Yo, whats popping my G!",
            time = "16:13",
            imageRes = null
        ),

        Messages(
            sent = false,
            message = "Yo, whats popping my G!",
            time = "16:16",
            imageRes = null
        ),

        Messages(
            sent = false,
            message = "Yo, whats popping my G!",
            time = "16:15",
            imageRes = null
        ),

        Messages(
            sent = true,
            message = "Yo, whats popping my G!",
            time = "16:14",
            imageRes = null
        ),

        Messages(
            sent = false,
            message = "Yo, whats popping my G!",
            time = "16:13",
            imageRes = null
        ),

        Messages(
            sent = true,
            message = "Yo, whats popping my G!",
            time = "16:21",
            imageRes = null
        ),

        Messages(
            sent = true,
            message = "Yo, whats popping my G!",
            time = "16:20",
            imageRes = null
        ),

        Messages(
            sent = false,
            message = "Yo, whats popping my G!",
            time = "16:19",
            imageRes = null
        ),

        Messages(
            sent = true,
            message = "Yo, whats popping my G!",
            time = "16:18",
            imageRes = null
        ),

        )

    fun getMessages(): List<Messages>{
        return messages
    }


}