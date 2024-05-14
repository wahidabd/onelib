package com.wahidabd.onelibrary.presentation.realtime

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.onelibrary.R
import com.wahidabd.onelibrary.databinding.ActivityRealtimeBinding
import com.wahidabd.onelibrary.domain.firebase.model.RealtimeParam
import com.wahidabd.onelibrary.presentation.realtime.adapter.RealtimeAdapter
import org.koin.android.ext.android.inject

class RealtimeActivity : BaseActivity<ActivityRealtimeBinding>() {

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
                onLoading = {
                    progress.visible()
                    rvFirestore.gone()
                    showToast("LOADING")
                },
                onFailure = { m ->
                    progress.gone()
                    rvFirestore.gone()
                    showToast(m.toString())
                },
                onEmpty = {
                    progress.gone()
                    rvFirestore.gone()
                    showToast("EMPTY")
                },
                onSuccess = {
                    progress.gone()
                    rvFirestore.visible()
                    realtimeAdapter.setData = it
                }
            )
        }
    }

    companion object{
        fun start(context: Context){
            context.startActivity(Intent(context, RealtimeActivity::class.java))
        }
    }

}