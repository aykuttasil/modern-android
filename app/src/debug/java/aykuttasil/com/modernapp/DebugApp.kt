package aykuttasil.com.modernapp

import aykuttasil.com.modernapp.util.extension.debug
import com.facebook.stetho.Stetho
import timber.log.Timber

class DebugApp : App() {

    override fun onCreate() {
        super.onCreate()
        initStetho()
    }

    fun initStetho() {
        debug {
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }
    }
}