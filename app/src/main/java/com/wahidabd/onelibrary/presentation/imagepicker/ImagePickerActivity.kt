package com.wahidabd.onelibrary.presentation.imagepicker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.onelibrary.databinding.ActivityImagePickerBinding

class ImagePickerActivity : BaseActivity<ActivityImagePickerBinding>() {

    override fun getViewBinding(): ActivityImagePickerBinding =
        ActivityImagePickerBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    override fun initObservers() {}


    companion object {
        fun start(context: Context){
            val intent = Intent(context, ImagePickerActivity::class.java)
            context.startActivity(intent)
        }
    }

}