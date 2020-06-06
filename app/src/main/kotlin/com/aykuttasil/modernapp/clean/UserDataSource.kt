package com.aykuttasil.modernapp.clean

interface UserDataSource {
  suspend fun addUser(user: User)
  suspend fun deleteUser(user: User)
  suspend fun getUserList(): List<User>
}