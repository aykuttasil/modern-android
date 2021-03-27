package com.aykuttasil.data.di

import com.aykuttasil.data.repositories.UserRepositoryImpl
import com.aykuttasil.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}