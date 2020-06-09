package com.aykuttasil.data.user

import com.aykuttasil.data.entities.UserData
import com.aykuttasil.data.entities.toUserEntity
import com.aykuttasil.domain.datastores.UserDataStore
import com.aykuttasil.domain.entities.UserEntity
import javax.inject.Inject
import javax.inject.Named

@Named("room_userdatastore")
class RoomUserDataStore @Inject constructor(private val userDao: UserDao) : UserDataStore {
  override suspend fun getUser(userName: String): UserEntity? {
    return userDao.getItem().toUserEntity()
  }

  override suspend fun saveUser(user: UserEntity): Boolean {
    // return userDao.insertItem(UserData()) != -1L
    return false
  }

}