package com.wahidabd.onelibrary.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahidabd.library.presentation.BaseActivity
import com.wahidabd.onelibrary.R
import com.wahidabd.onelibrary.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initAction() {
    }

    override fun initObservers() {
    }

    override fun initProcess() {
    }

    override fun initUI() {
        // set fragment with replace mode
        setFragment(R.id.mainContainer, TestFragment(), true)
    }

}