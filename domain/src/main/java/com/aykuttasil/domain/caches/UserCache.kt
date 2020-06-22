package com.aykuttasil.domain.caches

import com.aykuttasil.domain.entities.UserEntity

interface UserCache {
  suspend fun clear()
  suspend fun save(user: UserEntity): Boolean
  suspend fun remove(user: UserEntity): Boolean

  // fun saveAll(movieEntities: List<MovieEntity>)
  // fun getAll(): Observable<List<MovieEntity>>
  suspend fun get(): UserEntity?

  // fun search(query: String): Observable<List<MovieEntity>>
  suspend fun isEmpty(): Boolean
}