package com.example.instacloneapp3.data.local.room.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private  var Instance: UserDatabase? = null

        fun getDatabase(context : Context) : UserDatabase {
            //If the Instance is not null, return it, otherwise create a new one
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
                    .build()
                    .also{ Instance = it}
            }
        }
    }
}