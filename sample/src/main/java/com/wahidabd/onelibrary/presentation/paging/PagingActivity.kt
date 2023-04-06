package com.wahidabd.onelibrary.presentation.paging

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.onelibrary.databinding.ActivityPagingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class PagingActivity : BaseActivity<ActivityPagingBinding>() {

    private val viewModel: PagingViewModel by viewModel()
    private val mAdapter by lazy { TestPagingAdapter() }

    override fun getViewBinding(): ActivityPagingBinding =
        ActivityPagingBinding.inflate(layoutInflater)

    override fun initUI() {
        binding.rvMovie.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@PagingActivity)
        }
    }

    override fun initAction() {
    }

    override fun initProcess() {
        viewModel.paging()
    }

    override fun initObservers() {
        viewModel.paging.observe(this) {
            mAdapter.submitData(lifecycle, it)
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PagingActivity::class.java))
        }
    }

}