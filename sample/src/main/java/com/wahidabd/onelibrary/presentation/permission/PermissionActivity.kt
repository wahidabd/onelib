package com.wahidabd.onelibrary.presentation.permission

import android.content.Context
import android.content.Intent
import android.os.Build
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.presentation.dialog.materialAlertDialog
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.permission.isPermissionGranted
import com.wahidabd.library.utils.permission.oneRequestPermissions
import com.wahidabd.library.utils.permission.openPermissionAppSettings
import com.wahidabd.onelibrary.databinding.ActivityPermissionBinding


/**
 * Created by wahid on 5/22/2024.
 * Github github.com/wahidabd.
 */


class PermissionActivity : BaseActivity<ActivityPermissionBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PermissionActivity::class.java))
        }

        val permissions: Array<String>
            get() {
                val basePermissions = arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                )

                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    basePermissions + arrayOf(
                        android.Manifest.permission.POST_NOTIFICATIONS,
                    )
                } else {
                    basePermissions

                }
            }
    }

    override fun getViewBinding(): ActivityPermissionBinding {
        return ActivityPermissionBinding.inflate(layoutInflater)
    }

    override fun initIntent() {}

    override fun initUI() {
        showGrantedPermission()
    }

    override fun initAction() {
        binding.btnSendRequest.onClick {
            requestPermission()
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

    private fun requestPermission() {
        oneRequestPermissions(
            permissions = permissions,
            requestCode = 1120,
            doIfRationale = {
                materialAlertDialog {
                    setTitle("Permission Required")
                    setMessage("Please allow all permission to continue")
                    setPositiveButton("Allow") { _, _ ->
                        openPermissionAppSettings(this@PermissionActivity)
                    }
                    setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                }.show()
            },
            doIfGranted = {
                showGrantedPermission()
            }
        )
    }

    private fun showGrantedPermission() = with(binding) {
        tvCamera.text = "Camera : ${isPermissionGranted(android.Manifest.permission.CAMERA)}"
        tvStorage.text = "Storage : ${
            isPermissionGranted(android.Manifest.permission.READ_EXTERNAL_STORAGE) && isPermissionGranted(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }"
        tvLocation.text =
            "Location : ${isPermissionGranted(android.Manifest.permission.ACCESS_FINE_LOCATION)}"
        tvNotification.text = "Notification : ${
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                isPermissionGranted(android.Manifest.permission.POST_NOTIFICATIONS)
            } else {
                "Not Available"
            }
        }"
    }
}