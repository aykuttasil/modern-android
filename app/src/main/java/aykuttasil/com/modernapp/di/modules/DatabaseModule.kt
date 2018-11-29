package aykuttasil.com.modernapp.di.modules

import android.content.Context
import androidx.room.Room
import aykuttasil.com.modernapp.data.AppDatabase
import com.aykutasil.common.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    companion object {
        const val DB_NAME = "aa_2.db"
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase) = db.getUserDao()

    @Provides
    @Singleton
    fun provideLocationDao(db: AppDatabase) = db.getLocationDao()

}