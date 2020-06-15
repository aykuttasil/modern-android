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

class UserRepositoryImpl @Inject constructor(
  private val roomUserDataStore: RoomUserDataStore,
  private val inMemoryUserDataStore: InMemoryUserDataStore,
  private val remoteUserDataStore: RemoteUserDataStore
) : UserRepository {

  override suspend fun getUser(userName: String): UserEntity? {
    return if (inMemoryUserDataStore.getUser(userName) == null) {

      var user: UserEntity?
      try {
        user = remoteUserDataStore.getUser(userName)
        if (user != null) saveUser(user)
      } catch (ex: Exception) {
        Timber.e(ex)
        user = roomUserDataStore.getUser(userName)
      }
      user
    } else {
      val user = inMemoryUserDataStore.getUser(userName)
      Timber.i(user.toString())
      user
    }
  }

  override suspend fun saveUser(user: UserEntity): Boolean {
    inMemoryUserDataStore.saveUser(user)
    return roomUserDataStore.saveUser(user)
  }

}