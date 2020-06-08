package com.aykuttasil.modernapp

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.multidex.MultiDex
import com.aykuttasil.modernapp.di.components.DaggerAppComponent
import com.aykuttasil.common.util.Const
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import org.jetbrains.anko.notificationManager
import timber.log.Timber

@SuppressLint("Registered")
open class App : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent
      .builder()
      .application(this)
      .build()
  }

  override fun onCreate() {
    super.onCreate()
    initApp()
  }

  open fun initApp() {
    initTimber()
    // initFabric()
    initNotificationChannel()
  }

  private fun initTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      // Timber.plant(FabricTree())
    }
  }

  /*
  private fun initFabric() {
    val crashlyticsCore = CrashlyticsCore.Builder()
        .disabled(BuildConfig.DEBUG)
        .build()

    val crashlytics = Crashlytics.Builder()
        .core(crashlyticsCore)
        .build()

    Fabric.with(this, crashlytics)
  }

   */

  private fun initNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        Const.NOTIF_CHANNEL_ID,
        Const.NOTIF_CHANNEL_NAME,
        NotificationManager.IMPORTANCE_DEFAULT
      )
      notificationManager.createNotificationChannel(channel)
    }
  }

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)
    try {
      MultiDex.install(this)
    } catch (ex: Exception) {
    }
  }
}
