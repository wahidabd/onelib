package com.wahidabd.onelibrary.presentation.firestore.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.onelibrary.databinding.FirestoreItemBinding
import com.wahidabd.onelibrary.domain.firebase.model.FirestoreData


/**
 * Created by Wahid on 6/30/2023.
 * Github github.com/wahidabd.
 */


class FirestoreAdapter(
    private val context: Context,
    items: ArrayList<FirestoreData> = arrayListOf(),
    private val onItemClick: (data: FirestoreData) -> Unit,
    private val onDelete: (id: String) -> Unit,
) : BaseAsyncRecyclerAdapter<FirestoreData, FirestoreAdapter.FirestoreViewHolder>(items) {


    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return FirestoreItemBinding.inflate(context.layoutInflater, parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FirestoreAdapter.FirestoreViewHolder {
        return FirestoreViewHolder(getViewBinding(parent, viewType))
    }

    inner class FirestoreViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<FirestoreData>(binding) {
        override fun bind(data: FirestoreData) = with(binding as FirestoreItemBinding) {
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