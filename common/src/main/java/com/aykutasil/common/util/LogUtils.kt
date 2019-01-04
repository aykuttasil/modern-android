package com.aykutasil.common.util

import timber.log.Timber

object LogUtils {

    fun i(msg: String?) {
        Timber.i(msg ?: "non message")
    }

    fun d(msg: String?) {
        Timber.d(msg ?: "non message")
    }

    fun e(msg: String?) {
        Timber.e(msg ?: "non message")
    }

    fun e(throwable: Throwable, msg: String?) {
        Timber.e(throwable, msg ?: "non message")
    }

}