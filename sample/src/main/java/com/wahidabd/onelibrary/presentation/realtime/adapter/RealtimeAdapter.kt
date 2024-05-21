package com.wahidabd.onelibrary.presentation.realtime.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.onelibrary.databinding.FirestoreItemBinding
import com.wahidabd.onelibrary.domain.firebase.realtime.model.RealtimeData


/**
 * Created by Wahid on 7/2/2023.
 * Github github.com/wahidabd.
 */


class RealtimeAdapter(
    private val context: Context,
    items: ArrayList<RealtimeData> = arrayListOf(),
    private val onItemClick: (data: RealtimeData) -> Unit,
    private val onDelete: (id: String) -> Unit,
) : BaseAsyncRecyclerAdapter<RealtimeData, RealtimeAdapter.FirestoreViewHolder>() {


    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return FirestoreItemBinding.inflate(context.layoutInflater, parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RealtimeAdapter.FirestoreViewHolder {
        return FirestoreViewHolder(getViewBinding(parent, viewType))
    }

    inner class FirestoreViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<RealtimeData>(binding) {
        override fun bind(data: RealtimeData) = with(binding as FirestoreItemBinding) {
            tvName.text = data.name
            tvAge.text = data.age.toString()

            tvName.onClick {
                onItemClick.invoke(data)
            }
            imgDelete.onClick {
                onDelete.invoke(data.id.toString())
            }
        }
    }

}