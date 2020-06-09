package com.aykuttasil.data.repositories

import com.aykuttasil.data.user.InMemoryUserDataStore
import com.aykuttasil.data.user.RemoteUserDataStore
import com.aykuttasil.data.user.RoomUserDataStore
import com.aykuttasil.domain.caches.UserCache
import com.aykuttasil.domain.entities.UserEntity
import com.aykuttasil.domain.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl(
  private val roomUserDataStore: RoomUserDataStore,
  private val inMemoryUserDataStore: InMemoryUserDataStore,
  private val remoteUserDataStore: RemoteUserDataStore
) : UserRepository {

  override suspend fun getUser(userName: String): UserEntity? {
    return remoteUserDataStore.getUser(userName)
  }

  override suspend fun saveUser(user: UserEntity): Boolean {
    inMemoryUserDataStore.saveUser(user)
    return roomUserDataStore.saveUser(user)
  }

}