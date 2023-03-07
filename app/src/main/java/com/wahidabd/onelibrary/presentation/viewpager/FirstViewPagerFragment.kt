package com.wahidabd.onelibrary.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.onelibrary.databinding.FragmentFirstViewPagerBinding

class FirstViewPagerFragment : BaseFragment<FragmentFirstViewPagerBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentFirstViewPagerBinding =
        FragmentFirstViewPagerBinding.inflate(layoutInflater)

    override fun initUI() {
    }

    override fun initAction() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}