package com.wahidabd.library.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    lateinit var binding: VB

    abstract fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initProcess()
        initObservers()
        initAction()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun initAction()
    abstract fun initObservers()
    abstract fun initProcess()
    abstract fun initUI()

    interface Callback {
        abstract fun onFragmentAttached()
        abstract fun onFragmentDetached(tag: String)
    }

}