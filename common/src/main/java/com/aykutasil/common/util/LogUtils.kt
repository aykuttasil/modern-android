package com.aykutasil.common.util

import timber.log.Timber

object LogUtils {

    fun info(msg: String?) {
        Timber.i(msg)
    }

    fun d(msg: String?) {
        Timber.d(msg)
    }

}