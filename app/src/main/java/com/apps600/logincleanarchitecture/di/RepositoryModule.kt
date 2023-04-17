package com.apps600.logincleanarchitecture.di

import com.apps600.logincleanarchitecture.data.remote.ApiService
import com.apps600.logincleanarchitecture.data.repository.RepositoryImpl
import com.apps600.logincleanarchitecture.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService,ioDispatcher: CoroutineDispatcher): Repository{
        return RepositoryImpl(apiService,ioDispatcher)
    }
}