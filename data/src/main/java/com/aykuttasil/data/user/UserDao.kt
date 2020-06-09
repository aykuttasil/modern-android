package com.aykuttasil.data.user

import androidx.room.*
import com.aykuttasil.data.entities.UserData

@Dao
abstract class UserDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insertItem(item: UserData): Long

  @Update(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun updateItem(item: UserData)

  @Delete
  abstract suspend fun deleteItem(item: UserData)

  @Query("SELECT * FROM userdata")
  abstract suspend fun getItem(): UserData

}