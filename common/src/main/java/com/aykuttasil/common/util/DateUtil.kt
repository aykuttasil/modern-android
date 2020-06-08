/**
 * Designed and developed by Aykut Asil (@aykuttasil)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aykuttasil.common.util

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
