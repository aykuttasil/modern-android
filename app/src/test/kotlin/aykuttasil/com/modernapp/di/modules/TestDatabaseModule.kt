package aykuttasil.com.modernapp.di.modules

import android.content.Context
import androidx.room.Room
import aykuttasil.com.modernapp.data.AppDatabase
import com.aykutasil.modernapp.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestDatabaseModule : DatabaseModule() {

    @Provides
    @Singleton
    override
    fun provideDatabase(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
        context,
        AppDatabase::class.java
    ).fallbackToDestructiveMigration().build()

}