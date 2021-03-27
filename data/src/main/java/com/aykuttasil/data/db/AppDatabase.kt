package com.aykuttasil.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aykuttasil.data.entities.UserData
import com.aykuttasil.data.user.UserDao

@Database(
    entities = [UserData::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}