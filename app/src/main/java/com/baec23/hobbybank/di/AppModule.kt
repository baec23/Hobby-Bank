package com.baec23.hobbybank.di

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.baec23.hobbybank.service.NavService
import com.baec23.hobbybank.repository.DataStoreRepository
import com.baec23.hobbybank.repository.HobbyClassRepository
import com.baec23.hobbybank.repository.UserRepository
import com.baec23.hobbybank.service.SnackbarService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context) =
        DataStoreRepository(context)
    @Singleton
    @Provides
    fun provideNavController(@ApplicationContext context: Context) = NavHostController(context).apply {
        navigatorProvider.addNavigator(ComposeNavigator())
        navigatorProvider.addNavigator(DialogNavigator())
    }
    @Singleton
    @Provides
    fun provideNavService(navController: NavHostController) = NavService(navController = navController)

    @Singleton
    @Provides
    fun provideSnackbarService() = SnackbarService()
}