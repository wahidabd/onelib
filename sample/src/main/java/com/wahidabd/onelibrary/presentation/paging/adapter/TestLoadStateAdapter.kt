package com.wahidabd.onelibrary.presentation.paging.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.onelibrary.databinding.LayoutLoadStateAdapterBinding


/**
 * Created by wahid on 5/24/2024.
 * Github github.com/wahidabd.
 */


class TestLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<TestLoadStateAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: TestLoadStateAdapter.ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): TestLoadStateAdapter.ViewHolder {
        return ViewHolder(
            LayoutLoadStateAdapterBinding.inflate(
                parent.context.layoutInflater,
                parent,
                false
            )
        )
    }

    inner class ViewHolder(
        private val binding: LayoutLoadStateAdapterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) = with(binding) {
            if (loadState is LoadState.Error) tvError.text = loadState.error.message
            progressBar.isVisible = loadState is LoadState.Loading
            tvError.isVisible = loadState is LoadState.Error
            btnRetry.isVisible = loadState is LoadState.Error

            btnRetry.onClick {
                retry.invoke()
            }
        }
    }
}