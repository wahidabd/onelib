package com.wahidabd.onelibrary.presentation.multistateview

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showEmptyState
import com.wahidabd.library.utils.extensions.showErrorState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.onelibrary.R
import com.wahidabd.onelibrary.databinding.ActivityMultiStateViewBinding

class MultiStateViewActivity : BaseActivity<ActivityMultiStateViewBinding>() {

    override fun getViewBinding(): ActivityMultiStateViewBinding =
        ActivityMultiStateViewBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {
        with(binding){
            btnDefault.onClick {
                msvContent.showDefaultState()
            }

            btnLoading.onClick {
                msvContent.showLoadingState()
            }

            btnEmpty.onClick {
                msvContent.showEmptyState()
//                msvContent.showEmptyState(
//                    title = "Data not found",
//                    emptyMessage = "Empty result",
//                    drawable = ContextCompat.getDrawable(this@MultiStateViewActivity, R.drawable.ic_launcher_background)
//                )
            }

            btnError.onClick {
                msvContent.showErrorState()
//                    msvContent.showErrorState(
//                    title = "Whoops",
//                    errorMessage = "Error Occurred",
//                    drawable = ContextCompat.getDrawable(this@MultiStateViewActivity, R.drawable.ic_launcher_background),
//                    errorButton = Pair("Retry") { showToast("Retry to do this failed action") }
//                )
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, MultiStateViewActivity::class.java))
        }
    }

}