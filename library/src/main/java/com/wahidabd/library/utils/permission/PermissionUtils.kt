package com.wahidabd.library.utils.permission

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.tbruyelle.rxpermissions3.RxPermissions
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import java.util.*

fun Activity.openAppSettings(applicationId: String) {
    val intent = Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:$applicationId"))
    startActivity(intent)
}

fun AppCompatActivity.requestPermission(
    vararg permissions: String,
    onAllowed: () -> Unit,
    onDenied: () -> Unit
): Disposable {
    val rxPermissions = RxPermissions(this)
    return rxPermissions
        .requestEach(*permissions)
        .subscribe {
            if (it.granted) onAllowed.invoke()
            else onDenied.invoke()

            Timber.d("do we have permissions granted? $it")
        }
}

fun AppCompatActivity.requestPermission(
    vararg permissions: String,
    onAllowed: (String) -> Unit,
    onNeedPermissionRationale: (String) -> Unit,
    onDenied: (String) -> Unit
): Disposable {
    val rxPermissions = RxPermissions(this)
    return rxPermissions
        .requestEach(*permissions)
        .subscribe {
            when {
                it.granted -> {
                    onAllowed.invoke(it.name)
                }

                it.shouldShowRequestPermissionRationale -> {
                    onNeedPermissionRationale.invoke(it.name)
                }

                else -> {
                    onDenied.invoke(it.name)
                }
            }

            Timber.d("do we have permissions granted? $it")
        }
}

fun Fragment.requestPermission(
    vararg permissions: String,
    onAllowed: () -> Unit,
    onDenied: () -> Unit
): Disposable {
    val rxPermissions = RxPermissions(this)
    return rxPermissions
        .requestEach(*permissions)
        .subscribe {
            if (it.granted) onAllowed.invoke()
            else onDenied.invoke()

            Timber.d("do we have permissions granted? $it")
        }
}

fun Fragment.requestPermission(
    vararg permissions: String,
    onAllowed: (String) -> Unit,
    onNeedPermissionRationale: (String) -> Unit,
    onDenied: (String) -> Unit
): Disposable {
    val rxPermissions = RxPermissions(this)
    return rxPermissions
        .requestEach(*permissions)
        .subscribe {
            when {
                it.granted -> {
                    onAllowed.invoke(it.name)
                }

                it.shouldShowRequestPermissionRationale -> {
                    onNeedPermissionRationale.invoke(it.name)
                }

                else -> {
                    onDenied.invoke(it.name)
                }
            }

            Timber.d("do we have permissions granted? $it")
        }
}

fun Activity.showPermissionDialog(
    permissionName: String,
    requestCode: Int
){
    ActivityCompat.requestPermissions(this, arrayOf(permissionName), requestCode)
}