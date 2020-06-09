package com.aykuttasil.modernapp.util.extension

import android.app.Application
import com.aykuttasil.modernapp.BuildConfig

inline fun Application.debug(block: () -> Unit) {
  if (BuildConfig.DEBUG) {
    block()
  }
}
