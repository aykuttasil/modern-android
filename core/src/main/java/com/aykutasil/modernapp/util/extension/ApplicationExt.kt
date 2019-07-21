package com.aykutasil.modernapp.util.extension

import android.app.Application
import com.aykutasil.modernapp.BuildConfig

inline fun Application.debug(block: () -> Unit) {
  if (BuildConfig.DEBUG) {
    block()
  }
}
