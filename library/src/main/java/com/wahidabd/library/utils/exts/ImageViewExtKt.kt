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


/**
 * Extension function for ImageView to load an image from a URL with optional center cropping.
 *
 * @param context The context to use for loading the image.
 * @param imageUrl The URL of the image to load.
 * @param isCenterCrop Optional parameter to determine if the image should be center cropped. Defaults to false.
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


/**
 * Extension function for ImageView to load an image from a URL with a placeholder and optional center cropping.
 *
 * @param context The context to use for loading the image.
 * @param imageUrl The URL of the image to load.
 * @param placeholder The resource ID of the placeholder image to show while the image loads.
 * @param isCenterCrop Optional parameter to determine if the image should be center cropped. Defaults to false.
 */
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


/**
 * Extension function for ImageView to load an image from a URL with a placeholder, error image, and optional center cropping.
 *
 * @param context The context to use for loading the image.
 * @param imageUrl The URL of the image to load.
 * @param placeholder The resource ID of the placeholder image to show while the image loads.
 * @param error The resource ID of the error image to show if the image fails to load.
 * @param isCenterCrop Optional parameter to determine if the image should be center cropped. Defaults to false.
 */
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


/**
 * Extension function for Context to check if it is still valid.
 *
 * This function checks if the context is an Activity and ensures that it is not destroyed or finishing.
 *
 * @return True if the context is valid, false otherwise.
 */
fun Context.isValidContext(): Boolean {
    val activity = if (this is Activity) this else null
    return if (activity != null) (!activity.isDestroyed && !activity.isFinishing) else true
}