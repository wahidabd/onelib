package com.wahidabd.onelibrary.presentation.paging

import android.content.Context
import android.content.Intent
import com.wahidabd.library.decoration.onestateview.showContent
import com.wahidabd.library.decoration.onestateview.showLoading
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.exts.loadStateListener
import com.wahidabd.onelibrary.databinding.ActivityPagingBinding
import com.wahidabd.onelibrary.presentation.paging.adapter.TestLoadStateAdapter
import com.wahidabd.onelibrary.presentation.paging.adapter.TestPagingAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class PagingActivity : BaseActivity<ActivityPagingBinding>() {

    private val viewModel: PagingViewModel by viewModel()
    private val mAdapter by lazy { TestPagingAdapter() }

    override fun getViewBinding(): ActivityPagingBinding =
        ActivityPagingBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {
        binding.rvMovie.apply {
            adapter = mAdapter.withLoadStateHeaderAndFooter(
                header = TestLoadStateAdapter { mAdapter.retry() },
                footer = TestLoadStateAdapter { mAdapter.retry() }
            )
        }

        mAdapter.loadStateListener(
            notLoading = { binding.stateView.showContent() },
            loading = { binding.stateView.showLoading() },
            endOfPaging = { binding.stateView.showContent() }
        )
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