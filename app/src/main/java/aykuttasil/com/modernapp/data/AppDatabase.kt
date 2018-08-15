package aykuttasil.com.modernapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import aykuttasil.com.modernapp.data.local.dao.LocationDao
import aykuttasil.com.modernapp.data.local.dao.UserDao
import aykuttasil.com.modernapp.data.local.entity.LocationEntity
import aykuttasil.com.modernapp.data.local.entity.UserEntity
import aykuttasil.com.modernapp.util.converter.RoomTypeConverter

@Database(
        entities = [
            (UserEntity::class),
            (LocationEntity::class)
        ],
        version = 1)
@TypeConverters(RoomTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getLocationDao(): LocationDao
}