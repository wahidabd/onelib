package com.wahidabd.onelibrary.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.onelibrary.databinding.FragmentTestBinding

class TestFragment : BaseFragment<FragmentTestBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentTestBinding = FragmentTestBinding.inflate(layoutInflater)

    override fun initUI() {
        // Handle UI here, ex: set text of TextView, set adapter to RecyclerView, etc
    }

    override fun initAction() {
        // Handle Action here, ex: click button, etc
    }


    override fun initProcess() {
        // Handle Process here, ex: load data, etc
    }

    override fun initObservers() {
        // Handle Observers here, ex: LiveData, Flow, etc
    }

}