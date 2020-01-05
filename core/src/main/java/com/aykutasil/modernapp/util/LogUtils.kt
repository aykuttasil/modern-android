package com.aykutasil.modernapp.util

import timber.log.Timber

inline fun <reified T> T.logd(message: () -> String) = Timber.d(T::class.simpleName!!, message())

inline fun <reified T> T.loge(error: Throwable, message: () -> String) =
    Timber.e(T::class.simpleName!!, message(), error)