package com.wahidabd.onelibrary.presentation.adapter

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.onelibrary.databinding.ActivityAsyncRecyclerBinding
import com.wahidabd.onelibrary.utils.Constant

class AsyncRecyclerActivity : BaseActivity<ActivityAsyncRecyclerBinding>() {

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context, AsyncRecyclerActivity::class.java))
        }
    }

    private val asyncAdapter: TestBaseAsyncRecyclerAdapter by lazy {
        TestBaseAsyncRecyclerAdapter(
            context = this,
            onItemClicked = {}
        )
    }

    override fun getViewBinding(): ActivityAsyncRecyclerBinding =
        ActivityAsyncRecyclerBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {
        binding.rvItem.apply {
            adapter = asyncAdapter
            layoutManager = LinearLayoutManager(this@AsyncRecyclerActivity)
        }
    }

    override fun initAction() {
    }

    override fun initProcess() {
        asyncAdapter.setData = Constant.dataList()
    }

    override fun initObservers() {
    }


}