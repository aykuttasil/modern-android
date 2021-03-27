package com.aykuttasil.domain.datastores

import com.aykuttasil.domain.entities.UserEntity

interface UserDataStore {
    suspend fun getUser(userName: String): UserEntity?
    suspend fun saveUser(user: UserEntity): Boolean
    suspend fun deleteUser(): Boolean
}