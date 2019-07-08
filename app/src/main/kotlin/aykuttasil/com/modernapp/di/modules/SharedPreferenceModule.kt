package aykuttasil.com.modernapp.di.modules

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferenceModule {

  @Singleton
  @Provides
  internal fun provideSharedPreference(application: Application): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(application.applicationContext)
  }
}