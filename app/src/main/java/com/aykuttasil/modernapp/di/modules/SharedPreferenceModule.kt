package com.aykuttasil.modernapp.di.modules

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

    @Singleton
    @Provides
    internal fun provideSharedPreference(application: Application): SharedPreferences {
        return application.getSharedPreferences("MyApp", MODE_PRIVATE)
    }
}
