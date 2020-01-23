package aykuttasil.com.modernapp.clean

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
abstract class UserDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insertItem(item: UserEntity): Long

  @Update(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun updateItem(item: UserEntity)

  @Delete
  abstract suspend fun deletetItem(item: UserEntity)

  @Query("SELECT count(*) FROM user_entity")
  abstract suspend fun getItemsCount(): Int

  @Query("SELECT * FROM user_entity")
  abstract suspend fun getItems(): List<UserEntity>

}