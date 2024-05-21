package com.wahidabd.onelibrary.utils

import android.content.Context
import android.net.Uri
import com.yalantis.ucrop.util.FileUtils
import java.io.File


/**
 * Created by wahid on 5/21/2024.
 * Github github.com/wahidabd.
 */


fun uriToFile(context: Context, uri: Uri): File? {
    val filePath = FileUtils.getPath(context, uri)
    val file = File(filePath)

    return if (file.exists()) file
    else null
}