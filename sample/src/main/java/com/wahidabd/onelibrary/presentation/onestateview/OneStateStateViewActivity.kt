package com.wahidabd.onelibrary.presentation.onestateview

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.decoration.onestateview.showContent
import com.wahidabd.library.decoration.onestateview.showEmpty
import com.wahidabd.library.decoration.onestateview.showLoading
import com.wahidabd.onelibrary.databinding.ActivityOneStateViewBinding

class OneStateStateViewActivity : BaseActivity<ActivityOneStateViewBinding>() {

    override fun getViewBinding(): ActivityOneStateViewBinding =
        ActivityOneStateViewBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {
        with(binding) {
            btnDefault.onClick {
                viewState.showContent()
            }

            btnLoading.onClick {
                viewState.showLoading()
            }

            btnEmpty.onClick {
                viewState.showEmpty()
            }

            btnError.onClick {
                viewState.showCustomErrorState("This is error message") {
                    showToast("Button error clicked")
                }
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, OneStateStateViewActivity::class.java))
        }
    }

}