package com.aykuttasil.modernapp.util

import timber.log.Timber

inline fun <reified T> T.logd(message: () -> String) = Timber.d(message())

inline fun <reified T> T.loge(error: Throwable, message: () -> String) =
    Timber.e(error, message())