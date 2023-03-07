package com.wahidabd.onelibrary.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.onelibrary.databinding.FragmentSecondViewPagerBinding

class SecondViewPagerFragment : BaseFragment<FragmentSecondViewPagerBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentSecondViewPagerBinding =
        FragmentSecondViewPagerBinding.inflate(layoutInflater)

    override fun initUI() {
    }

    override fun initAction() {
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}