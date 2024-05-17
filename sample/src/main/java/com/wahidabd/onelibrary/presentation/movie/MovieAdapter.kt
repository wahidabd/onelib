package com.wahidabd.onelibrary.presentation.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.onelibrary.databinding.ItemMovieBinding
import com.wahidabd.onelibrary.domain.movie.model.Movie


/**
 * Created by wahid on 5/15/2024.
 * Github github.com/wahidabd.
 */


class MovieAdapter(
    private val context: Context
) : BaseAsyncRecyclerAdapter<Movie, MovieAdapter.MovieViewHolder>() {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(getViewBinding(parent, viewType))
    }

    inner class MovieViewHolder(
        binding: ViewBinding
    ) : BaseAsyncItemViewHolder<Movie>(binding) {
        override fun bind(data: Movie) = with(binding as ItemMovieBinding) {
            tvTitle.text = data.title
            imgPoster.setImageUrl(context, data.posterPath.orEmpty())
        }
    }
}