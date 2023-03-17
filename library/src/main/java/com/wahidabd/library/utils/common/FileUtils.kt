package com.wahidabd.library.utils.common

import android.content.Context
import android.os.Environment
import androidx.core.content.ContextCompat
import java.io.File

fun getRootDirPath(context: Context): String {
    return if (Environment.getExternalStorageState() == "mounted"){
        val file: File = ContextCompat.getExternalFilesDirs(context.applicationContext, null)[0]
        file.absolutePath
    }else{
        context.applicationContext.filesDir.absolutePath
    }
}

fun isFileExist(path: String): Boolean {
    val file = File(path)
    return file.exists() && !file.isDirectory
}