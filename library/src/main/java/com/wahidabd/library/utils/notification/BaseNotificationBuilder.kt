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
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.google.gson.Gson
import com.wahidabd.library.R
import org.jetbrains.annotations.NotNull

var NOTIFICATION_CHANNEL_ID = "app_notification_channel_id"
var NOTIFICATION_NAME = "notification_name"

private fun buildNotification(
    context: Context,
    title: String,
    description: String,
    intent: PendingIntent,
    imageBitmap: Bitmap?
) {
    if (VERSION.SDK_INT >= 26){
        val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, title, NotificationManager.IMPORTANCE_DEFAULT)
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

fun showNotification(
    context: Context,
    title: String,
    body: String,
    intent: PendingIntent,
    imgUrl: String? = null
) {
    if (imgUrl?.isNotEmpty() == true){
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
    }else{
        buildNotification(context, title, body, intent, null)
    }
}

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

public inline fun <reified T> Map<String, String>.parseJsonDataToObject(): T {
    val content = this["data"]
    val gson = Gson()
    return gson.fromJson(content, T::class.java)
}