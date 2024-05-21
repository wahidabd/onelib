package com.wahidabd.onelibrary.presentation.realtime

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.extensions.showDefaultState
import com.wahidabd.library.utils.extensions.showEmptyState
import com.wahidabd.library.utils.extensions.showErrorState
import com.wahidabd.library.utils.extensions.showLoadingState
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.onelibrary.databinding.ActivityRealtimeBinding
import com.wahidabd.onelibrary.domain.firebase.model.RealtimeParam
import com.wahidabd.onelibrary.presentation.realtime.adapter.RealtimeAdapter
import org.koin.android.ext.android.inject

class RealtimeActivity : BaseActivity<ActivityRealtimeBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RealtimeActivity::class.java))
        }
    }

    private val viewModel: RealtimeViewModel by inject()
    private val realtimeAdapter by lazy {
        RealtimeAdapter(
            this,
            onDelete = {},
            onItemClick = {}
        )
    }

    override fun getViewBinding(): ActivityRealtimeBinding =
        ActivityRealtimeBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {
        binding.rvFirestore.adapter = realtimeAdapter
    }

    override fun initAction() {
        with(binding) {
            btnSubmit.onClick {
                val name = edName.toStringTrim()
                val age = edAge.toStringTrim().toInt()

                val data = RealtimeParam(name = name, age = age)
                viewModel.add(data)
            }
        }
    }

    override fun initProcess() {
        viewModel.list()
    }

    override fun initObservers() {
        with(binding) {
            viewModel.list.observerLiveData(
                this@RealtimeActivity,
                onLoading = { msv.showLoadingState() },
                onFailure = { m ->
                    msv.showErrorState(m)
                },
                onEmpty = {
                    msv.showEmptyState()
                },
                onSuccess = {
                    msv.showDefaultState()
                    realtimeAdapter.setData = it
                }
            )
        }
    }
}