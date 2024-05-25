package com.wahidabd.onelibrary.presentation.localdatabase.adapter

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.onelibrary.databinding.ItemNoteBinding
import com.wahidabd.onelibrary.domain.note.model.Note


/**
 * Created by wahid on 5/25/2024.
 * Github github.com/wahidabd.
 */


class NoteAdapter(
    private val onDelete: (Note) -> Unit,
) : BaseAsyncRecyclerAdapter<Note, NoteAdapter.ViewHolder>() {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemNoteBinding.inflate(parent.context.layoutInflater, parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        return ViewHolder(ItemNoteBinding.inflate(parent.context.layoutInflater, parent, false))
    }

    inner class ViewHolder(
        binding: ViewBinding
    ) : BaseAsyncItemViewHolder<Note>(binding) {
        override fun bind(data: Note) = with(binding as ItemNoteBinding) {
            tvTitle.text = data.title
            tvBody.text = data.description

            ivDelete.onClick {
                onDelete(data)
            }
        }
    }
}