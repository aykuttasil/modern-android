package com.aykuttasil.modernapp.di.modules

import android.app.Application
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import org.jetbrains.anko.defaultSharedPreferences
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SharedPreferenceModule {

  @Singleton
  @Provides
  internal fun provideSharedPreference(application: Application): SharedPreferences {
    return application.defaultSharedPreferences
  }
}
