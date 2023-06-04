package com.example.instacloneapp3.data.network.retrofit

import com.example.instacloneapp3.data.network.data_transfer_objects.UserDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface UserApiService {
    @GET("/user/{email}")
    suspend fun getUser(@Path("email") email: String) : UserDto

//    @POST("/user")
}