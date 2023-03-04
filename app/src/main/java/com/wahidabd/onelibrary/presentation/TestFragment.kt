package com.wahidabd.onelibrary.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.BaseFragment
import com.wahidabd.onelibrary.databinding.FragmentTestBinding

class TestFragment : BaseFragment<FragmentTestBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentTestBinding = FragmentTestBinding.inflate(layoutInflater)

    override fun initAction() {
    }

    override fun initObservers() {
    }

    override fun initProcess() {
    }

    override fun initUI() {
    }

}