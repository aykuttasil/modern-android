package com.aykuttasil.data.di

import com.aykuttasil.data.repositories.UserRepositoryImpl
import com.aykuttasil.data.user.InMemoryUserDataStore
import com.aykuttasil.data.user.RemoteUserDataStore
import com.aykuttasil.data.user.RoomUserDataStore
import com.aykuttasil.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module(
  includes = [
    DatabaseModule::class,
    NetworkModule::class,
    ApiModule::class
  ]
)
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

  @Singleton
  @Binds
  abstract fun provideUserRepository(
    userRepositoryImpl: UserRepositoryImpl
  ): UserRepository
}