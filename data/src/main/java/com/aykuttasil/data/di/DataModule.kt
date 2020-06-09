package com.aykuttasil.data.di

import com.aykuttasil.data.repositories.UserRepositoryImpl
import com.aykuttasil.data.user.InMemoryUserDataStore
import com.aykuttasil.data.user.RemoteUserDataStore
import com.aykuttasil.data.user.RoomUserDataStore
import com.aykuttasil.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

  @Singleton
  @Provides
  fun provideUserRepository(
    roomUserDataStore: RoomUserDataStore,
    inMemoryUserDataStore: InMemoryUserDataStore,
    remoteUserDataStore: RemoteUserDataStore
  ): UserRepository {
    return UserRepositoryImpl(roomUserDataStore, inMemoryUserDataStore, remoteUserDataStore)
  }
}