package com.wahidabd.library.utils.exts

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build.VERSION
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/**
 * Created by Wahid on 4/5/2023.
 * Github github.com/wahidabd.
 */


@SuppressLint("CheckResult")
fun ImageView.setImageUrl(context: Context, imageUrl: String, isCenterCrop: Boolean? = false){
    if (context.isValidContext()){
        val options = RequestOptions()
        if (isCenterCrop == true){
            options.centerCrop()
        }

        Glide.with(context)
            .load(imageUrl)
            .apply(options)
            .into(this)
    }
}

@SuppressLint("CheckResult")
fun ImageView.setImageUrl(context: Context, imageUrl: String, placeholder: Int, isCenterCrop: Boolean = false){
    if (context.isValidContext()){
        val options = RequestOptions()
        options.error(placeholder)
        if (isCenterCrop) options.centerCrop()
        Glide.with(context)
            .load(imageUrl)
            .apply(options)
            .into(this)
    }
}

@SuppressLint("CheckResult")
fun ImageView.setImageUrl(context: Context, imageUrl: String, placeholder: Int, error: Int, isCenterCrop: Boolean = false){
    if (context.isValidContext()){
        val options = RequestOptions()
        options
            .placeholder(placeholder)
            .error(error)
        if (isCenterCrop) options.centerCrop()
        Glide.with(context)
            .load(imageUrl)
            .apply(options)
            .into(this)
    }
}

fun Context.isValidContext(): Boolean {
    val activity = if (this is Activity) this as Activity? else null
    return if (activity != null) (!activity.isDestroyed && !activity.isFinishing) else true
}