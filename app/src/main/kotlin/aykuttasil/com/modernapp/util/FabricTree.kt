package aykuttasil.com.modernapp.util

import com.crashlytics.android.Crashlytics
import timber.log.Timber

class FabricTree : Timber.Tree() {

  override fun log(
      priority: Int,
      tag: String?,
      message: String,
      t: Throwable?
  ) {
    if (t != null) {
      Crashlytics.setString("crash_tag", tag)
      Crashlytics.logException(t)
    } else {
      Crashlytics.log(priority, tag, message)
    }
  }
}