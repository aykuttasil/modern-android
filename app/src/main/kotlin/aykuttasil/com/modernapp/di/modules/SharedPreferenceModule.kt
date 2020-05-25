package aykuttasil.com.modernapp.di.modules

import android.app.Application
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import org.jetbrains.anko.defaultSharedPreferences
import javax.inject.Singleton

@Module
class SharedPreferenceModule {

  @Singleton
  @Provides
  internal fun provideSharedPreference(application: Application): SharedPreferences {
    return application.defaultSharedPreferences
  }
}
