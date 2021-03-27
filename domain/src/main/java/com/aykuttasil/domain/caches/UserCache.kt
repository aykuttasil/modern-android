package com.aykuttasil.domain.caches

import com.aykuttasil.domain.entities.UserEntity

interface UserCache {
    suspend fun clear()
    suspend fun save(user: UserEntity): Boolean
    suspend fun remove(user: UserEntity): Boolean
    suspend fun get(): UserEntity?
    suspend fun isEmpty(): Boolean
}