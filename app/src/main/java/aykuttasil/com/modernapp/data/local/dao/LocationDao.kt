package aykuttasil.com.modernapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import aykuttasil.com.modernapp.data.local.entity.LocationEntity
import io.reactivex.Flowable

@Dao
abstract class LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertItems(list: List<LocationEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertItem(item: LocationEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateItem(item: LocationEntity)

    @Delete
    abstract fun deleteItem(item: LocationEntity)

    @Query("SELECT * FROM location")
    abstract fun getItems(): Flowable<List<LocationEntity>>

    @Query("SELECT * FROM location")
    abstract fun getItemsLiveData(): LiveData<List<LocationEntity>>

    @Query("SELECT * FROM location WHERE locationId=:id")
    abstract fun getItem(id: Long): LocationEntity

    @Insert
    abstract fun insertAll(vararg locations: LocationEntity)
}