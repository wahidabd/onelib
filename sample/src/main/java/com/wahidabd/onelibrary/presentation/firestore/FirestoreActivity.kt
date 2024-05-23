package com.wahidabd.onelibrary.presentation.firestore

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.exts.clear
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.onelibrary.data.firebase.firestore.model.FirestoreRequest
import com.wahidabd.onelibrary.databinding.ActivityFirestoreBinding
import com.wahidabd.onelibrary.domain.firebase.firestore.model.FirestoreParam
import com.wahidabd.onelibrary.presentation.firestore.adapter.FirestoreAdapter
import org.koin.android.ext.android.inject

class FirestoreActivity : BaseActivity<ActivityFirestoreBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FirestoreActivity::class.java))
        }
    }

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

    override fun initIntent() {}

    override fun initUI() {
        binding.rvFirestore.adapter = firestoreAdapter
    }

    override fun initAction() {
        with(binding) {
            btnSubmit.onClick {

                val name = edName.toStringTrim()
                val age = edAge.toStringTrim()
                val address = edAddress.toStringTrim()

                val req = FirestoreParam(
                    name = name,
                    age = age.toInt(),
                    address = address,
                )
                viewModel.add(req)
            }

            btnUpdate.onClick {
                val name = edName.toStringTrim()

                val req = FirestoreRequest(name = name, id = "")
                viewModel.update(req)
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
                onLoading = { },
                onFailure = { m -> },
                onSuccess = {
                    clearText()
                }
            )

            viewModel.list.observerLiveData(
                this@FirestoreActivity,
                onLoading = { },
                onEmpty = { },
                onFailure = { m -> },
                onSuccess = {
                    firestoreAdapter.setData = it
                }
            )

            viewModel.remove.observerLiveData(
                this@FirestoreActivity,
                onLoading = { },
                onFailure = { m -> },
                onSuccess = {
                    viewModel.list()
                }
            )

            viewModel.data.observerLiveData(
                this@FirestoreActivity,
                onLoading = { },
                onFailure = { m -> },
                onSuccess = {}
            )
        }
    }

    private fun clearText() = with(binding) {
        edName.clear()
        edAge.clear()
        edAddress.clear()
    }

}