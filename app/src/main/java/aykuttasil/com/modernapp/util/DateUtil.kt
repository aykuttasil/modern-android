package aykuttasil.com.modernapp.util

import android.text.format.DateUtils


/**
 * Created by aykutasil on 13.02.2018.
 */
object DateUtil {
    /**
     * Converts epoch time to relative time span.
     *
     * @param time time epoch in seconds. i.e: 1496491779
     * @return relative time span compared with current. i.e: 5 minutes ago
     */
    fun formatRelativeTime(time: Long): String {
        return DateUtils.getRelativeTimeSpanString(time * 1000, System.currentTimeMillis(),
                android.text.format.DateUtils.MINUTE_IN_MILLIS).toString()
    }
}