package com.aykuttasil.data.user

import com.aykuttasil.domain.datastores.UserDataStore
import com.aykuttasil.domain.entities.UserEntity
import javax.inject.Inject
import javax.inject.Named

@Named("inmemory_userdatastore")
class InMemoryUserDataStore @Inject constructor() : UserDataStore {

  private var userEntity: UserEntity? = null

  override suspend fun getUser(userName: String): UserEntity? {
    return userEntity
  }

  override suspend fun saveUser(user: UserEntity): Boolean {
    this.userEntity = user
    return true
  }
}