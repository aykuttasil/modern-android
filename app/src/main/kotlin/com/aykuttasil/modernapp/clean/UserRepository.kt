package com.aykuttasil.modernapp.clean


/*
import javax.inject.Inject

interface UserRepository {
  suspend fun getUser(): User
  suspend fun deleteUser(user: User): Boolean
  suspend fun getUserList(): List<User>
  suspend fun addUser(user: User)
}

class UserRepositoryImpl @Inject constructor(var userDataSource: UserDataSource) : UserRepository {
  override suspend fun getUser(): User {
    return userDataSource.getUserList().first()
  }

  override suspend fun deleteUser(user: User): Boolean {
    return try {
      userDataSource.deleteUser(user)
      true
    } catch (ex: Exception) {
      ex.printStackTrace()
      false
    }
  }

  override suspend fun getUserList(): List<User> {
    return userDataSource.getUserList()
  }

  override suspend fun addUser(user: User) {
    userDataSource.addUser(user)
  }

}

 */