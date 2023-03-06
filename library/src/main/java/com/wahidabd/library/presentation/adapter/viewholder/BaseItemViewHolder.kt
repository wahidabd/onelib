package com.wahidabd.library.presentation.adapter.viewholder

import android.content.Context
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseRecyclerAdapter.*

abstract class BaseItemViewHolder<T>(
    context: Context?,
    val binding: ViewBinding,
    val mItemClickListener: OnItemClickListener?,
    private val mLongItemClickListener: OnLongItemClickListener?,
    private val isHeader: Boolean? = false
) : ViewHolder(binding.root), OnClickListener, OnLongClickListener {

    abstract fun bind(data: T)

    override fun onClick(v: View) {
        mItemClickListener?.onItemClick(
            v, if (isHeader == true)  this.adapterPosition - 1 else this.adapterPosition
        )
    }

    override fun onLongClick(v: View): Boolean {
        return if (mLongItemClickListener != null) {
            mLongItemClickListener.onLongItemClick(
                v, if (isHeader == true) this.adapterPosition - 1 else this.adapterPosition
            )
            true
        }else{
            false
        }
    }


}