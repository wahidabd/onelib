package com.wahidabd.onelibrary.data.movie.model.wrapper

data class MovieDataResponse<T>(
    val results: List<T>
)