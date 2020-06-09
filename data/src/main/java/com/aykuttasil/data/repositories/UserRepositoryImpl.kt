package com.aykuttasil.data.repositories

import com.aykuttasil.data.user.InMemoryUserDataStore
import com.aykuttasil.data.user.RemoteUserDataStore
import com.aykuttasil.data.user.RoomUserDataStore
import com.aykuttasil.domain.caches.UserCache
import com.aykuttasil.domain.entities.UserEntity
import com.aykuttasil.domain.repositories.UserRepository
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl(
  private val roomUserDataStore: RoomUserDataStore,
  private val inMemoryUserDataStore: InMemoryUserDataStore,
  private val remoteUserDataStore: RemoteUserDataStore
) : UserRepository {

  override suspend fun getUser(userName: String): UserEntity? {
    return if (inMemoryUserDataStore.getUser(userName) == null) {
      val user = remoteUserDataStore.getUser(userName)
      if (user != null) saveUser(user)
      user
    } else {
      Timber.i(inMemoryUserDataStore.getUser(userName).toString())
      UserEntity(userName = "This field is coming from inMemoryUserDataStore")
    }
  }

  override suspend fun saveUser(user: UserEntity): Boolean {
    inMemoryUserDataStore.saveUser(user)
    return roomUserDataStore.saveUser(user)
  }

}