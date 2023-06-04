package com.example.instacloneapp3.data.network.retrofit

import com.example.instacloneapp3.data.network.data_transfer_objects.PostsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiService {
    @GET("/post")
    suspend fun getPosts() : List<PostsDto>

    @GET("/post/{id}")
    suspend fun getPostById(@Path("id") id: String): PostsDto?
}