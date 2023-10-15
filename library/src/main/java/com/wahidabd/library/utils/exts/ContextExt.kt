package com.wahidabd.library.utils.exts

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.core.content.ContextCompat


/**
 * Created by Wahid on 3/22/2023.
 * Github wahidabd.
 */


fun Context.browse(url: String, newTask: Boolean = false): Boolean =
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        if (newTask) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        true
    }catch (e: Exception){
        false
    }

fun Context.dial(tel: String?) =
    this.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$tel")))

@SuppressLint("QueryPermissionsNeeded")
fun Context.email(email: String, subject: String, text: String): Boolean {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:")
    val arr = arrayOf(email)
    intent.putExtra(Intent.EXTRA_EMAIL, arr)
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    intent.putExtra(Intent.EXTRA_TEXT, text)

    return if (intent.resolveActivity(this.packageManager) != null){
        this.startActivity(intent)
        true
    }else{
        false
    }
}

fun Context.getCompatColor(@ColorRes resId: Int): Int =
    ContextCompat.getColor(this, resId)

fun Context.getCompatDrawable(@DrawableRes resId: Int): Drawable? =
    ContextCompat.getDrawable(this, resId)

fun Context.getDimenSize(@DimenRes resId: Int): Int =
    this.resources.getDimensionPixelSize(resId)

fun Context.getInteger(@IntegerRes resId: Int): Int =
    this.resources.getInteger(resId)

fun Context.isDarkTheme(): Boolean = (this.resources.configuration.uiMode) == 32

fun Context.makeCall(number: String): Boolean {
    TODO()
}

fun Context.rate(): Boolean =
    browse("http://play.google.com/store/apps/details?id=${this.packageManager}")

fun Context.sendSms(number: String, text: String): Boolean {
    TODO()
}

fun Context.share(text: String, subject: String): Boolean {
    val intent = Intent()
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, text)
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)

    return try {
        this.startActivity(Intent.createChooser(intent, null))
        true
    }catch (e: Exception){
        false
    }
}

fun Context.tintedDrawable(@DrawableRes drawableId: Int, @ColorRes colorId: Int): Drawable? {
    TODO()
}