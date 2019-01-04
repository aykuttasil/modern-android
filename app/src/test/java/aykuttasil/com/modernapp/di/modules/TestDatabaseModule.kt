package aykuttasil.com.modernapp.di.modules

import android.content.Context
import androidx.room.Room
import aykuttasil.com.modernapp.data.AppDatabase
import com.aykutasil.modernapp.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
        context,
        AppDatabase::class.java
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase) = db.getUserDao()

    @Provides
    @Singleton
    fun provideLocationDao(db: AppDatabase) = db.getLocationDao()

}