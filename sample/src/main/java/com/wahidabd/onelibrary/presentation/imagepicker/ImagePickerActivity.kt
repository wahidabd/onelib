package com.wahidabd.onelibrary.presentation.imagepicker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.permission.requestPermission
import com.wahidabd.library.utils.permission.showPermissionDialog
import com.wahidabd.onelibrary.databinding.ActivityImagePickerBinding

class ImagePickerActivity : BaseActivity<ActivityImagePickerBinding>() {

    override fun getViewBinding(): ActivityImagePickerBinding =
        ActivityImagePickerBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {
        requestPermission()
    }

    override fun initAction() {
        with(binding){
            btnOriginalImage.onClick {
                OriginalImageActivity.start(this@ImagePickerActivity)
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}


    private fun requestPermission(){
        requestPermission(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            onAllowed = { permission ->
                when(permission){
                    android.Manifest.permission.READ_MEDIA_IMAGES -> showToast("Permission media is granted")
                    android.Manifest.permission.CAMERA -> showToast("Permission camera is granted")
                    android.Manifest.permission.READ_EXTERNAL_STORAGE -> showToast("Permission storage is granted")
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE -> showToast("Permission storage is granted")
                }
            },
            onDenied = { permission ->
                when(permission){
                    android.Manifest.permission.CAMERA -> showPermissionDialog(
                        permission,
                        REQUEST_CAMERA
                    )
                    android.Manifest.permission.READ_EXTERNAL_STORAGE -> showPermissionDialog(
                        permission,
                        REQUEST_STORAGE
                    )
                    android.Manifest.permission.READ_MEDIA_IMAGES -> showPermissionDialog(
                        permission,
                        REQUEST_MEDIA
                    )
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE -> showPermissionDialog(
                        permission,
                        REQUEST_WRITE_STORAGE
                    )
                }
            },
            onNeedPermissionRationale = { permission ->
                when(permission){
                    android.Manifest.permission.CAMERA -> showToast("Camera cannot be use, please allow the permission")
                    android.Manifest.permission.READ_EXTERNAL_STORAGE -> showToast("Gallery cannot be use, please allow the permission")
                    android.Manifest.permission.READ_MEDIA_IMAGES -> showToast("Media cannot be use, please allow the permission")
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE -> showToast("Media cannot be use, please allow the permission")
                }}
        )
    }

    companion object {

        const val REQUEST_CAMERA = 111
        const val REQUEST_STORAGE = 112
        const val REQUEST_WRITE_STORAGE = 114
        const val REQUEST_MEDIA = 113

        fun start(context: Context){
            val intent = Intent(context, ImagePickerActivity::class.java)
            context.startActivity(intent)
        }

    }

}