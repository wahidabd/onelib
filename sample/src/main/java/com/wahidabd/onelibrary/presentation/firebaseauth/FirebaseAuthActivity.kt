package com.wahidabd.onelibrary.presentation.firebaseauth

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.onelibrary.databinding.ActivityFirebaseAuthBinding
import com.wahidabd.onelibrary.domain.firebase.auth.model.FirebaseAuthParam
import org.koin.android.ext.android.inject

class FirebaseAuthActivity : BaseActivity<ActivityFirebaseAuthBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FirebaseAuthActivity::class.java))
        }
    }

    private val viewModel: FirebaseAuthViewModel by inject()

    override fun getViewBinding(): ActivityFirebaseAuthBinding {
        return ActivityFirebaseAuthBinding.inflate(layoutInflater)
    }

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {
        binding.btnLogin.onClick { sendData(true) }
        binding.btnRegister.onClick { sendData(false) }
    }

    override fun initProcess() {}

    override fun initObservers() {
        viewModel.user.observerLiveData(
            this,
            onLoading = {
                debug { "LOADING" }
            },
            onFailure = { message ->
                debug { "ERROR" + message.toString() }
            },
            onSuccess = {
                debug { it.uid }
            }
        )
    }

    private fun sendData(isLogin: Boolean) = with(binding) {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        val body = FirebaseAuthParam(
            email = email,
            password = password
        )

        if (isLogin) viewModel.login(body)
        else viewModel.register(body)
    }
}