package com.wahidabd.library.presentation.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.viewholder.BaseItemViewHolder

abstract class BaseRecyclerAdapter<Data, Holder : BaseItemViewHolder<Data>>(
    private val context: Context,
    private val items: ArrayList<Data>
) : RecyclerView.Adapter<Holder>(){

    var onItemClickListener: OnItemClickListener? = null
    var onLongItemClickListener: OnLongItemClickListener? = null


    abstract fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    fun setLongItemClickListener(onLongItemClickListener: OnLongItemClickListener){
        this.onLongItemClickListener = onLongItemClickListener
    }

    fun add(item: Data){
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun add(item: Data, position: Int) {
        this.items.add(position, item)
        notifyItemInserted(position)
    }

    fun add(items: List<Data>) {
        val size = items.size
        for (i in 0 until size) {
            this.items.add(items[i])
        }
        notifyDataSetChanged()
    }


    fun addAll(items: List<Data>){
        this.add(items)
    }

    fun addOrUpdate(item: Data) {
        val i = this.items.indexOf(item)
        if (i >= 0) {
            this.items[i] = item
            this.notifyItemChanged(i)
        } else {
            this.add(item)
        }
    }

    fun addOrUpdate(items: List<Data>) {
        val size = items.size
        for (i in 0 until size) {
            val item = items[i]
            val x = this.items.indexOf(item)
            if (x >= 0) {
                this.items[x] = item
            } else {
                this.add(item)
            }
        }
        notifyDataSetChanged()
    }

    fun addOrUpdateToFirst(items: List<Data>) {
        val size = items.size
        for (i in 0 until size) {
            val item = items[i]
            val x = this.items.indexOf(item)
            if (x >= 0) {
                this.items[x] = item
            } else {
                this.add(item, 0)
            }
        }
        notifyDataSetChanged()
    }

    fun addToFirst(item: Data) {
        this.items.add(0, item)
        notifyDataSetChanged()
    }

    fun addToFirst(items: List<Data>?) {
        this.items.addAll(0, items!!)
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        if (position >= 0 && position < this.items.size) {
            this.items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun remove(item: Data) {
        val position = this.items.indexOf(item)
        this.remove(position)
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface OnLongItemClickListener {
        fun onLongItemClick(view: View, position: Int)
    }
}