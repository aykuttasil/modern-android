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
package com.aykuttasil.modernapp.util

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.aykuttasil.modernapp.R
import com.aykuttasil.modernapp.common.util.Const

/**
 * Helper class for showing and canceling new message
 * notifications.
 *
 *
 * This class makes heavy use of the [NotificationCompat.Builder] helper
 * class to create notifications in a backward-compatible way.
 */
object NewMessageNotification {
    /**
     * The unique identifier for this type of notification.
     */
    private const val NOTIFICATION_TAG = "NewMessage"

    /**
     * Shows the notification, or updates a previously shown notification of
     * this type, with the given parameters.
     *
     *
     * TODO: Customize this method's arguments to present relevant content in
     * the notification.
     *
     *
     * TODO: Customize the contents of this method to tweak the behavior and
     * presentation of new message notifications. Make
     * sure to follow the
     * [
     * Notification design guidelines](https://developer.android.com/design/patterns/notifications.html) when doing so.
     *
     * @see .cancel
     */
    @JvmStatic
    fun notify(context: Context, exampleString: String, number: Int) {
        val res = context.resources

        // This image is used as the notification's large icon (thumbnail).
        // TODO: Remove this if your notification has no relevant thumbnail.
        val picture = BitmapFactory.decodeResource(res, R.drawable.example_picture)

        val title = res.getString(R.string.new_message_notification_title_template, exampleString)
        val text = res.getString(R.string.new_message_notification_placeholder_text_template, exampleString)

        val builder = NotificationCompat.Builder(context, Const.NOTIF_CHANNEL_ID)

            // Set appropriate defaults for the notification light, sound,
            // and vibration.
            .setDefaults(Notification.DEFAULT_ALL)

            // Set required fields, including the small icon, the
            // notification title, and text.
            .setSmallIcon(R.drawable.ic_stat_new_message)
            .setContentTitle(title)
            .setContentText(text)

            // All fields below this line are optional.

            // Use a default priority (recognized on devices running Android
            // 4.1 or later)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            // Provide a large icon, shown with the notification in the
            // notification drawer on devices running Android 3.0 or later.
            .setLargeIcon(picture)

            // Set ticker text (preview) information for this notification.
            .setTicker(exampleString)

            // Show a number. This is useful when stacking notifications of
            // a single type.
            .setNumber(number)

            // If this notification relates to a past or upcoming event, you
            // should set the relevant time information using the setWhen
            // method below. If this call is omitted, the notification's
            // timestamp will by set to the time at which it was shown.
            // TODO: Call setWhen if this notification relates to a past or
            // upcoming event. The sole argument to this method should be
            // the notification timestamp in milliseconds.
            // .setWhen(...)

            // Set the pending intent to be initiated when the user touches
            // the notification.
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    0,
                    Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")),
                    PendingIntent.FLAG_UPDATE_CURRENT))

            // Show expanded text content on devices running Android 4.1 or
            // later.
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(text)
                .setBigContentTitle(title)
                .setSummaryText("Dummy summary text"))

            // Example additional actions for this notification. These will
            // only show on devices running Android 4.1 or later, so you
            // should ensure that the activity in this notification's
            // content intent provides access to the same actions in
            // another way.
            .addAction(
                R.drawable.ic_action_stat_share,
                res.getString(R.string.action_share),
                PendingIntent.getActivity(
                    context,
                    0,
                    Intent.createChooser(Intent(Intent.ACTION_SEND)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_TEXT, "Dummy text"), "Dummy title"),
                    PendingIntent.FLAG_UPDATE_CURRENT))
            .addAction(
                R.drawable.ic_action_stat_reply,
                res.getString(R.string.action_reply), null)

            // Automatically dismiss the notification when it is touched.
            .setAutoCancel(true)

        notify(context, builder.build())
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private fun notify(context: Context, notification: Notification) {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(NOTIFICATION_TAG, 0, notification)
    }

    /**
     * Cancels any notifications of this type previously shown using
     * [.notify].
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    fun cancel(context: Context) {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.cancel(NOTIFICATION_TAG, 0)
    }
}
