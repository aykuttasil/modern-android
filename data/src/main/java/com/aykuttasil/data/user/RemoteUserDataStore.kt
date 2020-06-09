package com.aykuttasil.data.user

import com.aykuttasil.data.entities.toUserEntity
import com.aykuttasil.data.remote.ApiService
import com.aykuttasil.domain.datastores.UserDataStore
import com.aykuttasil.domain.entities.UserEntity
import javax.inject.Inject
import javax.inject.Named

@Named("remote_userdatastore")
class RemoteUserDataStore @Inject constructor(private val apiService: ApiService) : UserDataStore {
  override suspend fun getUser(userName: String): UserEntity? {
    return apiService.getUser(userName).toUserEntity()
  }

  override suspend fun saveUser(user: UserEntity): Boolean {
    return false
  }
}