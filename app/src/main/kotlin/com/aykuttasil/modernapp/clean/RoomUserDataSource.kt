package com.aykuttasil.modernapp.clean

import javax.inject.Inject

class RoomUserDataSource @Inject constructor(var userDao: UserDao) : UserDataSource {

  override suspend fun addUser(user: User) {
    userDao.insertItem(user.toUserEntity())
  }

  override suspend fun deleteUser(user: User) {
    userDao.deleteItem(user.toUserEntity())
  }

  override suspend fun getUserList(): List<User> {
    return userDao.getItems().map { it.toUser() }
  }
}