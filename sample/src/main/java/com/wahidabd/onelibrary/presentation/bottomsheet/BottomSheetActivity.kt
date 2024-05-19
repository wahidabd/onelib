package com.wahidabd.onelibrary.presentation.bottomsheet

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.onelibrary.databinding.ActivityBottomSheetBinding

class BottomSheetActivity : BaseActivity<ActivityBottomSheetBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, BottomSheetActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityBottomSheetBinding {
        return ActivityBottomSheetBinding.inflate(layoutInflater)
    }

    override fun initIntent() {
        // Handle intent here
    }

    override fun initUI() {
        // Handle UI here
    }

    override fun initAction() {
        binding.btnOpen.onClick {
            BottomSheetFragment.newInstance {
                showToast(it)
            }.show(supportFragmentManager, BottomSheetFragment::class.java.name)
        }
    }

    override fun initProcess() {
        // Handle process here
    }

    override fun initObservers() {
        // Handle observer here
    }

}