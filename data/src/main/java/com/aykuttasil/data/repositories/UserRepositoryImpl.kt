package com.aykuttasil.data.repositories

import com.aykuttasil.data.user.InMemoryUserDataStore
import com.aykuttasil.data.user.RemoteUserDataStore
import com.aykuttasil.data.user.RoomUserDataStore
import com.aykuttasil.domain.entities.UserEntity
import com.aykuttasil.domain.repositories.UserRepository
import com.aykuttasil.domain.usecases.user.LoginParams
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
  private val roomUserDataStore: RoomUserDataStore,
  private val inMemoryUserDataStore: InMemoryUserDataStore,
  private val remoteUserDataStore: RemoteUserDataStore
) : UserRepository {


  override suspend fun getUser(userName: String): UserEntity? {
    var user = inMemoryUserDataStore.getUser(userName)
    if (user == null) {
      user = roomUserDataStore.getUser(userName)
      if (user == null) {
        user = remoteUserDataStore.getUser(userName)
        if (user == null) throw Exception("Remote user couldn't be fetching!")
      }
    }
    saveUser(user)
    return user
  }

  override suspend fun saveUser(user: UserEntity): Boolean {
    inMemoryUserDataStore.saveUser(user)
    return roomUserDataStore.saveUser(user)
  }

  override suspend fun deleteUser(): Boolean {
    inMemoryUserDataStore.deleteUser()
    roomUserDataStore.deleteUser()
    remoteUserDataStore.deleteUser()
    return true
  }

  override suspend fun login(params: LoginParams): UserEntity? {
    return null
  }


}