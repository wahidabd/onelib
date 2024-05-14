package com.wahidabd.library.utils.permission

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ComponentActivity


fun Activity.showPermissionDialog(
    permissionName: String,
    requestCode: Int
) {
    ActivityCompat.requestPermissions(this, arrayOf(permissionName), requestCode)
}

fun AppCompatActivity.requestMultiplePermission(
    permissions: Array<String>,
    requestCode: Int,
    doIfGranted: (() -> Unit)? = null,
) {
    val deniedPermissions = mutableListOf<String>()
    permissions.forEach { permission ->
        if (!isGranted(permission)) {
            deniedPermissions.add(permission)
        }
    }
    if (deniedPermissions.isNotEmpty()) {
        ActivityCompat.requestPermissions(this, deniedPermissions.toTypedArray(), requestCode)
    } else {
        doIfGranted?.invoke()
    }
}

fun AppCompatActivity.isGranted(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}
/**
 * Compose Permission
 */

fun ComponentActivity.requestMultiplePermission(
    permissions: Array<String>,
    requestCode: Int,
    doIfGranted: (() -> Unit)? = null
) {
    val deniedPermissions = mutableListOf<String>()
    permissions.forEach { permission ->
        if (!isGranted(permission)) deniedPermissions.add(permission)
    }

    if (deniedPermissions.isNotEmpty()) {
        ActivityCompat.requestPermissions(this, deniedPermissions.toTypedArray(), requestCode)
    } else doIfGranted?.invoke()
}

fun ComponentActivity.isGranted(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}
