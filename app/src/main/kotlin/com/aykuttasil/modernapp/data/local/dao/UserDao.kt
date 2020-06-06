/**
 * Designed and developed by Aykut Asil (@aykuttasil)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aykuttasil.modernapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aykuttasil.modernapp.data.local.entity.UserEntity
import io.reactivex.Flowable

@Dao
abstract class UserDao {

  @Insert
  abstract fun insertAll(vararg users: UserEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insertItems(list: List<UserEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insertItem(item: UserEntity): Long

  @Update(onConflict = OnConflictStrategy.REPLACE)
  abstract fun updateItem(item: UserEntity)

  @Delete
  abstract fun deleteItem(item: UserEntity)

  @Query("SELECT count(*) FROM user")
  abstract fun getItemsCount(): Flowable<Int>

  @Query("SELECT * FROM user")
  abstract fun getItems(): Flowable<List<UserEntity>>

  @Query("SELECT * FROM user")
  abstract fun getItemsLiveData(): LiveData<List<UserEntity>>

  @Query("SELECT * FROM user WHERE userId=:id")
  abstract fun getItem(id: Long): UserEntity

  @Query("SELECT * FROM user WHERE userId=:id")
  abstract fun getItemLiveData(id: Long): LiveData<UserEntity>

  @Query("SELECT * FROM user WHERE userEmail=:username")
  abstract fun getItem(username: String): LiveData<UserEntity>
}
