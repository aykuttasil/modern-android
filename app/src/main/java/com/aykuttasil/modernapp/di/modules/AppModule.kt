package com.aykuttasil.modernapp.di.modules

import android.app.Application
import com.aykuttasil.modernapp.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [ImplModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    internal fun provideApp(application: Application): App {
        return application.applicationContext as App
    }

}
