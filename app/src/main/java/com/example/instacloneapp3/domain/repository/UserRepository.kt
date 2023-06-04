package com.example.instacloneapp3.domain.repository

import com.example.instacloneapp3.core.util.UILoadState
import com.example.instacloneapp3.data.network.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(email : String): Flow<UILoadState<User>>
//    suspend fun insertUser(user : User)
}