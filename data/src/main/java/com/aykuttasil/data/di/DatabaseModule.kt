package com.aykuttasil.data.di

import android.content.Context
import androidx.room.Room
import com.aykuttasil.common.di.ApplicationContext
import com.aykuttasil.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DatabaseModule {

  companion object {
    const val DB_NAME = "a2a_5.db"
  }

  @Provides
  @Singleton
  open fun provideDatabase(@ApplicationContext context: Context) =
    Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      DB_NAME
    ).fallbackToDestructiveMigration().build()

  @Provides
  @Singleton
  fun provideUserDao(db: AppDatabase) = db.getUserDao()
}
