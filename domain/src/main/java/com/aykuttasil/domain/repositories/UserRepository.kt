package com.aykuttasil.domain.repositories

import com.aykuttasil.domain.entities.UserEntity
import com.aykuttasil.domain.usecases.user.LoginParams

interface UserRepository {
    suspend fun getUser(userName: String): UserEntity?
    suspend fun saveUser(user: UserEntity): Boolean
    suspend fun deleteUser(): Boolean
    suspend fun login(params: LoginParams): UserEntity?
}