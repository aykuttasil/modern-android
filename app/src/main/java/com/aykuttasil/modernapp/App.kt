package com.aykuttasil.modernapp

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
open class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        initApp()
    }

    open fun initApp() {
        initTimber()
        // initNotificationChannel()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /*
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
    */

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}
