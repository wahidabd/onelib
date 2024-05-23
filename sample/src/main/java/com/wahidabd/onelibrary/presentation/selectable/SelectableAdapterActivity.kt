package com.wahidabd.onelibrary.presentation.selectable

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.exts.debug
import com.wahidabd.library.utils.exts.toSelectable
import com.wahidabd.onelibrary.databinding.ActivitySelectableAdapterBinding

class SelectableAdapterActivity : BaseActivity<ActivitySelectableAdapterBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SelectableAdapterActivity::class.java))
        }
    }

    private val adapter by lazy {
        SelectableAdapter(this) { item ->
            debug { item }
        }
    }

    override fun getViewBinding(): ActivitySelectableAdapterBinding {
        return ActivitySelectableAdapterBinding.inflate(layoutInflater)
    }

    override fun initIntent() {}

    override fun initUI() {
        binding.rvSelected.adapter = adapter
    }

    override fun initAction() {}

    override fun initProcess() {
        val data = mutableListOf<String>()
        for (i in 0..40) {
            data.add("Item $i")
        }

        adapter.setData = data.toSelectable()
    }

    override fun initObservers() {}


}