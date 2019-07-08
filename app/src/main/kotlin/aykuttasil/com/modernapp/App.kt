package aykuttasil.com.modernapp

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.multidex.MultiDex
import aykuttasil.com.modernapp.di.components.DaggerAppComponent
import aykuttasil.com.modernapp.util.extension.debug
import com.aykutasil.modernapp.util.Const
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.fabric.sdk.android.Fabric
import org.jetbrains.anko.notificationManager
import timber.log.Timber

@SuppressLint("Registered")
open class App : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder()
      .application(this)
      .build()
  }

  override fun onCreate() {
    super.onCreate()
    initTimber()
    initFabric()
    initNotificationChannel()
  }

  private fun initTimber() {
    debug {
      Timber.plant(Timber.DebugTree())
    }
  }

  private fun initFabric() {
    val crashlyticsCore = CrashlyticsCore.Builder()
      .disabled(BuildConfig.DEBUG)
      .build()

    val crashlytics = Crashlytics.Builder()
      .core(crashlyticsCore)
      .build()

    Fabric.with(this, crashlytics)
  }

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
    MultiDex.install(this)
  }
}