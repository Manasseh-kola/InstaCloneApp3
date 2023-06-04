package com.example.instacloneapp3.domain.usecase

import com.example.instacloneapp3.core.util.UILoadState
import com.example.instacloneapp3.data.network.models.User
import com.example.instacloneapp3.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(user : String): Flow<UILoadState<User>>{
        if(user.isBlank()){
            return flow {  }
        }
        return repository.getUser(user)
    }
}