package com.aykutasil.modernapp.util

import timber.log.Timber

// Quick & dirty logcat extensions
inline fun <reified T> T.logd(message: () -> String) = LogUtils.d(T::class.simpleName!!, message())

inline fun <reified T> T.loge(error: Throwable, message: () -> String) =
  LogUtils.e(T::class.simpleName!!, message(), error)


object LogUtils {

  fun i(msg: String?) {
    Timber.i(msg ?: "non message")
  }

  fun d(msg: String?) {
    Timber.d(msg ?: "non message")
  }

  fun d(tag: String, msg: String?) {
    d("""$tag : ${msg ?: "non message"}""")
  }

  fun e(msg: String?) {
    Timber.e(msg ?: "non message")
  }

  fun e(throwable: Throwable, msg: String?) {
    Timber.e(throwable, msg ?: "non message")
  }

  fun e(tag: String, msg: String?, throwable: Throwable) {
    e(throwable, """$tag : ${msg ?: "non message"}""")
  }

}