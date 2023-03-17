package com.wahidabd.library.utils.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

fun convertDpToPixel(dp: Float, context: Context): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi.toFloat() / 160f)
}

fun emptyString(): String = ""

fun getBitmapFromDrawable(drawable: Drawable): Bitmap {
    val bmp = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bmp)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bmp
}

fun getColoredString(text: String, color: Int): SpannableString {
    val word = SpannableString(text)
    word.setSpan(ForegroundColorSpan(color), 0, word.length, 33)
    return word
}

fun getCurrentDate(format: String, locale: Locale): String {
    return SimpleDateFormat(format, locale).format(Date())
}

@SuppressLint("HardwareIds")
fun getDeviceId(context: Context): String =
    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

fun getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

fun hideSoftKeyboard(view: View, context: Context?) {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun isMyAppRunning(context: Context, packageName: String): Boolean {
    TODO()
}

fun isMyServiceRunning(context: Context, serviceClass: Class<*>): Boolean {
    TODO()
}

fun isNetworkConnected(): Boolean {
    val connectivityManager = ContextProvider.get().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}

fun loadJSONFromAsset(context: Context, jsonFileName: String): String {
    val manager = context.assets
    val inputStream = manager.open(jsonFileName)
    val buffer = byteArrayOf(inputStream.available().toByte())
    inputStream.read(buffer)
    inputStream.close()
    val name = Charset.forName("UTF-8")
    return String(buffer, name)
}

fun objectToMap(o: Any): Map<String, String>? {
    val gson = Gson()
    val json = gson.toJson(o, o.javaClass)
    var map: Map<*, *>
    val type = object : TypeToken<Any?>() {}.type
    val res = gson.fromJson<Map<String, String>>(json, type)
    map = res
    return map
}

fun showKeyboard(view: View, context: Context?) {
    if (view.requestFocus()) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(view, 1)
    }
}