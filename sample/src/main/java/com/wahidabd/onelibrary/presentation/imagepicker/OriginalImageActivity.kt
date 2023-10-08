package com.wahidabd.onelibrary.presentation.imagepicker

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.cameraonly.CameraOnlyConfig
import com.esafirm.imagepicker.features.registerImagePicker
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.onelibrary.databinding.ActivityOriginalImageBinding


class OriginalImageActivity : BaseActivity<ActivityOriginalImageBinding>() {

    override fun getViewBinding(): ActivityOriginalImageBinding =
        ActivityOriginalImageBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {
        binding.imgOriginalImageCamera.onClick {
            launcherCamera.launch(CameraOnlyConfig(returnMode = ReturnMode.CAMERA_ONLY))
        }

        binding.imgOriginalImageGallery.onClick {
            launcherGallery.launch(
                ImagePickerConfig(
                    ImagePickerMode.SINGLE,
                    returnMode = ReturnMode.GALLERY_ONLY
                )
            )
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

    private val launcherCamera = registerImagePicker {
        it.forEach { image ->
            binding.imgOriginalImageCamera.setImageURI(image.uri)
            binding.imgOriginalImageCamera.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

    private val launcherGallery = registerImagePicker {
        it.forEach { image ->
            binding.imgOriginalImageGallery.setImageURI(image.uri)
            binding.imgOriginalImageGallery.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

    companion object {
        const val CAMERA_REQUEST_CODE = 122
        const val GALLERY_REQUEST_CODE = 123

        fun start(context: Context) {
            context.startActivity(Intent(context, OriginalImageActivity::class.java))
        }
    }

}