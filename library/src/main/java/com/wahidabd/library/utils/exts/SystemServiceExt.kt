package com.wahidabd.library.utils.exts

import android.app.DownloadManager
import android.app.job.JobScheduler
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat

val Context.clipboardManager: ClipboardManager
    get() = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

val Context.connectivityManager: ConnectivityManager
    get() = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

val Context.downloadManager: DownloadManager
    get() = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

val Context.inputMethodManager: InputMethodManager
    get() = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

val Context.jobScheduler: JobScheduler
    get() = this.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

val Context.layoutInflater: LayoutInflater
    get() = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

val Context.telephonyManager: TelephonyManager
    get() = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

val Context.windowManager: WindowManager
    get() = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager

fun Context.copyToClipboard(content: String) {
    val clipboardManager = ContextCompat.getSystemService(this, ClipboardManager::class.java)
    val clip = ClipData.newPlainText(Context.CLIPBOARD_SERVICE, content)
    clipboardManager?.setPrimaryClip(clip)
}