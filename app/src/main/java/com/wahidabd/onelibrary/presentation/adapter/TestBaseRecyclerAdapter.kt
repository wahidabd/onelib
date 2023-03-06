package com.wahidabd.onelibrary.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseItemViewHolder
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.onelibrary.data.TestModel
import com.wahidabd.onelibrary.databinding.ItemRecyclerBinding

class TestBaseRecyclerAdapter(
    private val context: Context,
    items: ArrayList<TestModel>,
    private val onItemClicked: ((TestModel) -> Unit)?
) : BaseRecyclerAdapter<TestModel, TestBaseRecyclerAdapter.ViewHolder>(context, items) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemRecyclerBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestBaseRecyclerAdapter.ViewHolder {
        return ViewHolder(
            context,
            getViewBinding(parent, viewType),
            onItemClickListener,
            onLongItemClickListener
        )
    }

    inner class ViewHolder(
        mContext: Context,
        binding: ViewBinding,
        itemClickListener: OnItemClickListener?,
        longItemClickListener: OnLongItemClickListener?
    ) : BaseItemViewHolder<TestModel>(mContext, binding, itemClickListener, longItemClickListener) {

        override fun bind(data: TestModel) {
            with(binding as ItemRecyclerBinding) {
                tvTitle.text = data.title

                root.onClick {
                    onItemClicked?.invoke(data)
                }
            }
        }

    }
}