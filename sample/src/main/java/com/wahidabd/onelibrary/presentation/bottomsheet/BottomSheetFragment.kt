package com.wahidabd.onelibrary.presentation.bottomsheet

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseBottomSheetDialogFragment
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.onelibrary.R
import com.wahidabd.onelibrary.databinding.FragmentBottomSheetBinding


class BottomSheetFragment : BaseBottomSheetDialogFragment<FragmentBottomSheetBinding>() {

    companion object {
        fun newInstance(
            data: (String) -> Unit
        ): BottomSheetFragment = BottomSheetFragment().apply {
            this.data = data
        }
    }

    private var data: (String) -> Unit = {}

    override val bottomSheetTheme: Int = R.style.OnelibBottomDialog
    override val tagName: String = BottomSheetFragment::class.java.name

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentBottomSheetBinding {
        return FragmentBottomSheetBinding.inflate(layoutInflater, container, attachRoot)
    }

    override fun initUI() {
        // Handle UI here
    }

    override fun initAction() {
        binding.btnClose.onClick {
            data.invoke("Data from bottom sheet")
            dismiss()
        }
    }

    override fun initProcess() {
        // Handle process here
    }

    override fun initObservers() {
        // Handle observer here
    }

}