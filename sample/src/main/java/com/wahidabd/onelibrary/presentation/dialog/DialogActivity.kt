package com.wahidabd.onelibrary.presentation.dialog

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.presentation.dialog.materialAlertDialog
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.onelibrary.databinding.ActivityDialogBinding

class DialogActivity : BaseActivity<ActivityDialogBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DialogActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityDialogBinding {
        return ActivityDialogBinding.inflate(layoutInflater)
    }

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {
        binding.btnMaterialDialog.onClick {
            materialAlertDialog {
                setTitle("MaterialAlertDialog")
                setMessage("This is a MaterialAlertDialog")
                setIcon(com.wahidabd.library.R.drawable.ic_notifications)
                setPositiveButton("OK") { dialog, _ ->
                    showToast("OK Clicked")
                    dialog.dismiss()
                }
                setNegativeButton("Cancel") { dialog, _ ->
                    showToast("Cancel Clicked")
                    dialog.dismiss()
                }
            }.show()
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}