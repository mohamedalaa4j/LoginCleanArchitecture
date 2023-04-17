package com.apps600.logincleanarchitecture.di

import com.apps600.logincleanarchitecture.domain.repository.Repository
import com.apps600.logincleanarchitecture.domain.useCase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(repository: Repository): LoginUseCase {
        return LoginUseCase(repository)
    }
}