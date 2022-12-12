package com.baec23.hobbybank.di

import com.baec23.hobbybank.repository.HobbyClassRepository
import com.baec23.hobbybank.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideHobbyClassRepository() = HobbyClassRepository()

    @Singleton
    @Provides
    fun provideUserRepository() = UserRepository()
}