package com.wahidabd.onelibrary.presentation.permission

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.permission.requestPermission
import com.wahidabd.library.utils.permission.showPermissionDialog
import com.wahidabd.onelibrary.databinding.ActivityPermissionBinding

class PermissionActivity : BaseActivity<ActivityPermissionBinding>() {

    override fun getViewBinding(): ActivityPermissionBinding =
        ActivityPermissionBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {
    }

    override fun initAction() {
        binding.btnSendRequest.onClick {
            requestPermission()
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    private fun requestPermission() {
        requestPermission(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            onAllowed = { permission ->
                when (permission) {
                    android.Manifest.permission.CAMERA -> binding.tvCamera.text =
                        "Camera permission is granted"
                    android.Manifest.permission.ACCESS_FINE_LOCATION -> binding.tvLocation.text =
                        "Fine location permission is granted"
                    android.Manifest.permission.READ_EXTERNAL_STORAGE -> binding.tvStorage.text =
                        "External storage permission is granted"
                }
            },
            onDenied = { permission ->
                when (permission) {
                    android.Manifest.permission.CAMERA -> showPermissionDialog(
                        permission,
                        CAMERA_REQUEST_CODE
                    )
                    android.Manifest.permission.ACCESS_FINE_LOCATION -> showPermissionDialog(
                        permission,
                        FINE_LOCATION_REQUEST_CODE
                    )
                    android.Manifest.permission.READ_EXTERNAL_STORAGE -> showPermissionDialog(
                        permission,
                        EXTERNAL_STORAGE_REQUEST_CODE
                    )
                }
            },
            onNeedPermissionRationale = { permission ->
                when (permission) {
                    android.Manifest.permission.CAMERA -> binding.tvCamera.text =
                        "Camera cannot be use, you need to allow"
                    android.Manifest.permission.ACCESS_FINE_LOCATION -> binding.tvLocation.text =
                        "Fine location cannot be use, you need to allow"
                    android.Manifest.permission.READ_EXTERNAL_STORAGE -> binding.tvStorage.text =
                        "External storage cannot be use, you need to allow"
                }
            },
        )
    }

    companion object {
        const val CAMERA_REQUEST_CODE = 1112
        const val FINE_LOCATION_REQUEST_CODE = 1113
        const val EXTERNAL_STORAGE_REQUEST_CODE = 1114

        fun start(context: Context) {
            val intent = Intent(context, PermissionActivity::class.java)
            context.startActivity(intent)
        }
    }

}