package com.wahidabd.onelibrary.data.movie.model.wrapper

import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse


/**
 * Created by wahid on 5/24/2024.
 * Github github.com/wahidabd.
 */


data class MoviePagingResponse(
    val page: Int,
    val results: List<MovieResultResponse>,
)