package com.example.instacloneapp3.dependecyinjection

import android.app.Application
import androidx.activity.viewModels
import androidx.room.Room
import com.example.instacloneapp3.core.util.EnvironmentalVariables
import com.example.instacloneapp3.data.local.room.user.UserDatabase
import com.example.instacloneapp3.data.network.retrofit.UserApiService
import com.example.instacloneapp3.data.repository.UserCacheThroughRepo
import com.example.instacloneapp3.domain.usecase.GetUserUseCase
import com.example.instacloneapp3.presentation.view_models.UserViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideGetUserUseCase(repository: UserCacheThroughRepo) : GetUserUseCase{
        return GetUserUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        apiService: UserApiService,
        database: UserDatabase
    ) : UserCacheThroughRepo{
        return UserCacheThroughRepo(apiService, database.userDao())
    }

    @Provides
    @Singleton
    fun providesUserDatabase(app:Application): UserDatabase{
        return Room.databaseBuilder(app, UserDatabase::class.java, "user_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApiService(): UserApiService{
        return Retrofit.Builder()
            .baseUrl(EnvironmentalVariables.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)
    }
}