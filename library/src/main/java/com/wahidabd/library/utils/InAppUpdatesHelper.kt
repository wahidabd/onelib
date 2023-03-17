package com.wahidabd.library.utils

import android.app.Activity
import android.content.Intent
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import org.koin.core.component.KoinComponent

class InAppUpdatesHelper : KoinComponent {

    private val RC_APP_UPDATE = 11
    private lateinit var appUpdateManager: AppUpdateManager

    fun init(activity: Activity?, appName: String?) {}

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    fun popupDialogForCompleteUpdate(
        title: String,
        message: String,
        positiveAction: String,
        onClicked: (() -> Unit)
    ) {

    }

    fun resume() {}

    fun start() {}

    fun startInAppUpdateFlow(appUpdateInfo: AppUpdateInfo) {

    }

    fun stop() {}

}