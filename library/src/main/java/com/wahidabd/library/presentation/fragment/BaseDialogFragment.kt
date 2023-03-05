package com.wahidabd.library.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment <VB: ViewBinding> : DialogFragment() {

    lateinit var binding: VB
    abstract val tagName: String

    abstract val isDialogCancelable: Boolean

    abstract fun getViewBinding(layoutInflater: LayoutInflater, container: ViewGroup?, attachRoot: Boolean): VB

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
        isCancelable = isDialogCancelable

        if (dialog != null){
            dialog?.window?.setSoftInputMode(16)
            dialog?.window?.setGravity(17)
            dialog?.window?.setLayout(-2, -2)
        }

        initUI()
        initAction()
        initProcess()
    }

    fun showDialog(fragmentManager: FragmentManager){
        val manager = fragmentManager.findFragmentByTag(tagName)
        if (manager != null){
            show(fragmentManager, tagName)
        }
    }


    abstract fun initAction()
    abstract fun initProcess()
    abstract fun initUI()

}