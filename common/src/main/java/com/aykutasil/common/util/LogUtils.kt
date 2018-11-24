package com.aykutasil.common.util

import timber.log.Timber

object LogUtils {

    fun info(msg: String?) {
        Timber.i(msg)
    }

}