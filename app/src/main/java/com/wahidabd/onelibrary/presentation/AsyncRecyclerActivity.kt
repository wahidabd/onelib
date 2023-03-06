package com.wahidabd.onelibrary.presentation

import android.provider.ContactsContract.Data
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.onelibrary.databinding.ActivityAsyncRecyclerBinding
import com.wahidabd.onelibrary.presentation.adapter.TestBaseAsyncRecyclerAdapter
import com.wahidabd.onelibrary.utils.Constant

class AsyncRecyclerActivity : BaseActivity<ActivityAsyncRecyclerBinding>() {

    private val asyncAdapter: TestBaseAsyncRecyclerAdapter by lazy {
        TestBaseAsyncRecyclerAdapter(
            context = this,
            items = mutableListOf(),
            onItemClicked = {}
        )
    }

    override fun getViewBinding(): ActivityAsyncRecyclerBinding =
        ActivityAsyncRecyclerBinding.inflate(layoutInflater)

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