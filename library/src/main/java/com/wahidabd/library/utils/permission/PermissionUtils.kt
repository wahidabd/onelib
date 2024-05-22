package com.wahidabd.library.utils.permission

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

/**
 * Requests a set of permissions and handles the logic for permission rationale and granted permissions.
 *
 * @param permissions An array of permissions to request.
 * @param requestCode The request code to use for the permission request callback.
 * @param doIfRationale An optional lambda to execute if the system decides to show a rationale for the permission request. Can be null.
 * @param doIfGranted An optional lambda to execute if all requested permissions are already granted. Can be null.
 *
 * This function performs the following steps:
 * - Checks each permission in the provided array to determine if it is already granted.
 * - If there are any denied permissions, it requests those permissions using ActivityCompat.requestPermissions.
 * - If a rationale is needed for any permission, it invokes the doIfRationale lambda if provided.
 * - If all permissions are already granted, it invokes the doIfGranted lambda if provided.
 * Example usage:
 * ```
 * oneRequestPermissions(
 *    permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE),
 *    requestCode = 123,
 *    doIfRationale = { showRationaleDialog() },
 *    doIfGranted = { openCamera() }
 * )
 * ```
 */
fun AppCompatActivity.oneRequestPermissions(
    permissions: Array<String>,
    requestCode: Int,
    doIfRationale: (() -> Unit)? = null,
    doIfGranted: (() -> Unit)? = null,
) {
    val deniedPermissions = mutableListOf<String>()
    permissions.forEach { permission ->
        if (!isPermissionGranted(permission)) {
            deniedPermissions.add(permission)
        }
    }

    if (deniedPermissions.isNotEmpty()) {
        ActivityCompat.requestPermissions(this, deniedPermissions.toTypedArray(), requestCode)
        doIfRationale?.invoke()
    } else {
        doIfGranted?.invoke()
    }
}

/**
 * Checks if a specific permission is granted.
 *
 * @param permission The permission to check.
 * @return A Boolean value indicating whether the permission is granted (true) or not (false).
 *
 * This function performs the following steps:
 * - Uses ActivityCompat.checkSelfPermission to determine if the specified permission is granted.
 * - Returns true if the permission is granted, false otherwise.
 */
fun AppCompatActivity.isPermissionGranted(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

/**
 * Opens the application settings screen for the current app to allow the user to manually grant permissions.
 *
 * @param context The context used to start the settings activity.
 *
 * This function performs the following steps:
 * - Creates an intent with the ACTION_APPLICATION_DETAILS_SETTINGS action.
 * - Sets the data for the intent to the package URI of the current application.
 * - Starts the activity to display the app settings screen.
 */
fun openPermissionAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    context.startActivity(intent)
}