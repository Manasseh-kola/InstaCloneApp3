package com.example.instacloneapp3.data.repository


import com.example.instacloneapp3.core.util.UILoadState
import com.example.instacloneapp3.data.local.room.user.UserDao
import com.example.instacloneapp3.data.network.data_transfer_objects.toUserEntity
import com.example.instacloneapp3.data.network.models.User
import com.example.instacloneapp3.data.network.retrofit.UserApiService
import com.example.instacloneapp3.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class UserCacheThroughRepo (
    private val apiService: UserApiService,
    private val dao: UserDao
) : UserRepository {
    override fun getUser(email: String): Flow<UILoadState<User>> = flow {
        emit(UILoadState.Loading())


        //Read data from cache/database
        val user = dao.getUser(email).toRemoteUser()
        emit(UILoadState.Loading(user))

        //Make Api request
        try {
            val remoteUser = apiService.getUser(email)
            //Update Cache
            dao.upsertUser(remoteUser.toUserEntity())
        }catch (e : HttpException){
            emit(UILoadState.Error(
                message = "Oops something went wrong",
                data = user
            ))
        }catch (e : IOException){
            emit(UILoadState.Error(
                message = "Could not read server, check your connection",
                data = user
            ))
        }

        val updatedUser = dao.getUser(email).toRemoteUser()
        emit(UILoadState.Success(data = updatedUser))
    }
}