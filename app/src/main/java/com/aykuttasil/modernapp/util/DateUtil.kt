package com.aykuttasil.modernapp.util

import android.text.format.DateUtils

object DateUtil {

    /**
     * Converts epoch time to relative time span.
     *
     * @param time time epoch in seconds. i.e: 1496491779
     * @return relative time span compared with current. i.e: 5 minutes ago
     */
    fun formatRelativeTime(time: Long): String {
        return DateUtils.getRelativeTimeSpanString(
            time * 1000, System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ).toString()
    }
}
