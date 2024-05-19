package com.wahidabd.onelibrary.presentation.selectable

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseSelectableAdapter
import com.wahidabd.library.presentation.adapter.Selectable
import com.wahidabd.onelibrary.databinding.ItemSelectableBinding


/**
 * Created by wahid on 5/15/2024.
 * Github github.com/wahidabd.
 */


class SelectableAdapter(
    private val context: Context,
    selectableItem: (selected: String) -> Unit
) : BaseSelectableAdapter<String>(isMultipleSelect = true, selectableItem = selectableItem) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemSelectableBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun bindData(binding: ViewBinding, data: Selectable<String>) {
        with(binding as ItemSelectableBinding) {
            tvTitle.text = data.item
        }
    }

    override fun setSelected(binding: ViewBinding, selected: Boolean) =
        with(binding as ItemSelectableBinding) {
            ivCheck.isSelected = selected
        }

}