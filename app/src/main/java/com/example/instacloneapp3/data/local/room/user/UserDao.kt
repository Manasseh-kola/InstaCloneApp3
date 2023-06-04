package com.example.instacloneapp3.data.local.room.user

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user : UserEntity)

    @Query("SELECT * from user_ WHERE email = :email")
    suspend fun getUser(email : String): UserEntity
}