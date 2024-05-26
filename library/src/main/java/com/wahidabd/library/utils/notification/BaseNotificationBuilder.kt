package com.wahidabd.library.utils.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build.VERSION
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.gson.Gson
import com.wahidabd.library.R

var NOTIFICATION_CHANNEL_ID = "app_notification_channel_id"
var NOTIFICATION_NAME = "notification_name"

/**
 * Builds and displays a notification with customizable elements such as title, description, intent, and an optional image.
 *
 * @param context The context in which the notification will be built and displayed.
 * @param title The title of the notification.
 * @param description The description or main text of the notification.
 * @param intent The pending intent to be triggered when the notification is tapped.
 * @param imageBitmap An optional bitmap image to be used in the notification. Can be null.
 *
 * This function performs the following steps:
 * - If the Android version is Oreo (API level 26) or higher, a NotificationChannel is created with the specified ID and title, and sound is disabled.
 * - Constructs the notification using NotificationCompat.Builder.
 * - Sets the content intent, small icon, title, and description.
 * - Enables auto-cancel to remove the notification when tapped.
 * - Sets the sound from the notification manager.
 * - Configures large icon and big picture style if an image bitmap is provided.
 * - Configures big text style for the description.
 * - Displays the constructed notification using the system's notification manager.
 */
private fun buildNotification(
    context: Context,
    title: String,
    description: String,
    intent: PendingIntent,
    imageBitmap: Bitmap?
) {
    if (VERSION.SDK_INT >= 26) {
        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            title,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationChannel.setSound(null, null)
    }

    val notificationCompat = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        .setContentIntent(intent)
        .setSmallIcon(R.drawable.ic_notifications)
        .setContentTitle(title)
        .setContentText(description)
        .setAutoCancel(true)
        .setSound(context.notificationManager().second)
        .setLargeIcon(imageBitmap)
        .setStyle(
            NotificationCompat.BigPictureStyle()
                .bigLargeIcon(imageBitmap)
                .bigPicture(imageBitmap)
        )
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(description)
        )

    context.notificationManager()
        .first
        .notify(0, notificationCompat.build())
}


/**
 * Constructs and displays a notification with customizable elements such as title, body, intent, and an optional image URL.
 * If an image URL is provided, it loads the image using Glide and includes it in the notification. If no image URL is provided or if loading the image fails, the notification is displayed without an image.
 *
 * @param context The context in which the notification will be displayed.
 * @param title The title of the notification.
 * @param body The body text of the notification.
 * @param intent The pending intent to be triggered when the notification is tapped.
 * @param imgUrl The URL of the image to be included in the notification. Optional.
 *
 * This function performs the following steps:
 * - Checks if an image URL is provided and not empty.
 * - If an image URL is provided, loads the image using Glide and sets up a CustomTarget to handle the bitmap resource.
 * - If the image loading fails or no image URL is provided, constructs the notification with a null image bitmap.
 * - Calls the buildNotification function to actually construct and display the notification.
 *
 * Example usage:
 * ```
 * val context: Context = // get context
 * val title = "New Notification"
 * val body = "You have a new notification!"
 * val intent = PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
 * val imgUrl: String? = "https://example.com/image.jpg" // Optional URL of the image
 *
 * showNotification(context, title, body, intent, imgUrl)
 * ```
 */
fun showNotification(
    context: Context,
    title: String,
    body: String,
    intent: PendingIntent,
    imgUrl: String? = null
) {
    if (imgUrl?.isNotEmpty() == true) {
        Glide.with(context)
            .asBitmap()
            .load(imgUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    buildNotification(context, title, body, intent, resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    buildNotification(context, title, body, intent, null)
                }

            })
    } else {
        buildNotification(context, title, body, intent, null)
    }
}

/**
 * Extension function for `Context` to obtain a `NotificationManager` and a default sound URI.
 *
 * This function retrieves the `NotificationManager` from the system services and the default notification sound URI.
 * If the Android version is Oreo (API level 26) or higher, it also creates a notification channel with specified settings.
 *
 * @receiver The context from which the function is called.
 * @return A pair containing the `NotificationManager` and the default sound URI.
 *
 * Details:
 * - Retrieves the `NotificationManager` system service.
 * - Gets the default notification sound URI using `RingtoneManager.getDefaultUri`.
 * - For API level 26 and above, creates a `NotificationChannel` with high importance, enabling lights, setting the light color, and enabling vibration with a specific pattern.
 *
 * Example usage:
 * ```
 * val (notificationManager, defaultSoundUri) = context.notificationManager()
 * ```
 */
private fun Context.notificationManager(): Pair<NotificationManager, Uri> {
    val notificationManager =
        this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val defaultSoundUri = RingtoneManager.getDefaultUri(2)
    if (VERSION.SDK_INT >= 26) {
        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = -1
        notificationChannel.enableVibration(true)

        val vibration = longArrayOf(100L, 200L, 300L, 400L, 500L, 400L, 300L, 200L, 400L)
        notificationChannel.vibrationPattern = vibration
        notificationManager.createNotificationChannel(notificationChannel)
    }

    return Pair(notificationManager, defaultSoundUri)
}

inline fun <reified T> Map<String, String>.parseJsonDataToObject(): T {
    val content = this["data"]
    val gson = Gson()
    return gson.fromJson(content, T::class.java)
}