package com.wahidabd.onelibrary.presentation.firestore

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.clear
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.onelibrary.databinding.ActivityFirestoreBinding
import com.wahidabd.onelibrary.domain.firebase.model.FirestoreParam
import com.wahidabd.onelibrary.presentation.firestore.adapter.FirestoreAdapter
import org.koin.android.ext.android.inject

class FirestoreActivity : BaseActivity<ActivityFirestoreBinding>() {

    private val viewModel: FirestoreViewModel by inject()
    private val firestoreAdapter by lazy {
        FirestoreAdapter(
            context = this,
            onItemClick = {
                viewModel.data(it.id.toString())
            },
            onDelete = {
                viewModel.remove(it)
            }
        )
    }

    override fun getViewBinding(): ActivityFirestoreBinding =
        ActivityFirestoreBinding.inflate(layoutInflater)

    override fun initUI() {
        binding.rvFirestore.adapter = firestoreAdapter
    }

    override fun initAction() {
        with(binding) {
            btnSubmit.onClick {

                val name = edName.toStringTrim()
                val age = edAge.toStringTrim()
                val address = edAddress.toStringTrim()

                val req = FirestoreParam(name = name, age = age.toInt(), address = address)
                viewModel.add(req)
            }
        }
    }

    override fun initProcess() {
        viewModel.list()
    }

    override fun initObservers() {
        with(binding) {
            viewModel.add.observerLiveData(
                this@FirestoreActivity,
                onLoading = {
                    debug { "ON LOADING" }
                },
                onFailure = { t, m ->
                    debug { "$t --> $m" }
                },
                onSuccess = {
                    edName.clear()
                    edAge.clear()
                    edAddress.clear()
                }
            )

            viewModel.list.observerLiveData(
                this@FirestoreActivity,
                onLoading = {
                    progress.visible()
                    rvFirestore.gone()
                },
                onEmpty = {
                    progress.gone()
                    rvFirestore.gone()
                    showToast("Data is empty")
                },
                onFailure = { t, m ->
                    progress.gone()
                    rvFirestore.gone()
                    showToast("$t -> $m")
                },
                onSuccess = {
                    progress.gone()
                    rvFirestore.visible()
                    firestoreAdapter.setData = it
                }
            )

            viewModel.remove.observerLiveData(
                this@FirestoreActivity,
                onLoading = {
                    debug { "ON LOADING" }
                },
                onFailure = { t, m ->
                    debug { "$t --> $m" }
                },
                onSuccess = {
                    debug { "Item Remove" }
                    viewModel.list()
                }
            )

            viewModel.data.observerLiveData(
                this@FirestoreActivity,
                onLoading = {
                    debug { "ON LOADING" }
                },
                onFailure = { t, m ->
                    debug { "$t --> $m" }
                },
                onSuccess = {
                    debug { "Data --> $it" }
                }
            )
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FirestoreActivity::class.java))
        }
    }

}