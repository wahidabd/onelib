package com.wahidabd.library.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<VB : ViewBinding> : BottomSheetDialogFragment() {

    lateinit var binding: VB
    abstract val bottomSheetTheme: Int
    abstract val tagName: String

    abstract fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): VB

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

    override fun getTheme(): Int {
        return this.bottomSheetTheme
    }

    fun showBottomSheet(fragmentManager: FragmentManager) {
        this.show(fragmentManager, tagName)
    }

    abstract fun initAction()
    abstract fun initObservers()
    abstract fun initProcess()
    abstract fun initUI()

}