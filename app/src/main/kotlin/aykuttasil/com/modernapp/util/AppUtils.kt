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
package aykuttasil.com.modernapp.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import aykuttasil.com.modernapp.R

object AppUtils {

  fun openPlayStoreForApp(context: Context) {
    val appPackageName = context.packageName
    try {
      context.startActivity(
        Intent(
          Intent.ACTION_VIEW,
          Uri.parse(
            context
              .resources
              .getString(R.string.app_market_link) + appPackageName
          )
        )
      )
    } catch (e: ActivityNotFoundException) {
      context.startActivity(
        Intent(
          Intent.ACTION_VIEW,
          Uri.parse(
            context
              .resources
              .getString(R.string.app_google_play_store_link) + appPackageName
          )
        )
      )
    }
  }
}
