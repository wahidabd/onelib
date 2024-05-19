package com.wahidabd.onelibrary.presentation.filterable

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.exts.onTextChange
import com.wahidabd.onelibrary.databinding.ActivityFilterableBinding

class FilterableActivity : BaseActivity<ActivityFilterableBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FilterableActivity::class.java))
        }
    }

    private val filterableAdapter by lazy {
        FilterableAdapter()
    }

    override fun getViewBinding(): ActivityFilterableBinding {
        return ActivityFilterableBinding.inflate(layoutInflater)
    }

    override fun initIntent() {}

    override fun initUI() {
        binding.rvItem.adapter = filterableAdapter
    }

    override fun initAction() {
        binding.edtSearch.onTextChange {
            filterableAdapter.filter.filter(it)
        }
    }

    override fun initProcess() {
        val data = mutableListOf<String>()
        for (i in 0..100) {
            data.add("Item $i")
        }
        filterableAdapter.setData = data
    }

    override fun initObservers() {}

}