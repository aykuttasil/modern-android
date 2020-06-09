package com.aykuttasil.domain.repositories

import com.aykuttasil.domain.entities.UserEntity

interface UserRepository {
  suspend fun getUser(userName: String): UserEntity?
  suspend fun saveUser(user: UserEntity): Boolean
}