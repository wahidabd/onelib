package com.wahidabd.onelibrary.domain.movie.mapper

import com.wahidabd.onelibrary.domain.movie.model.Movie
import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse

fun MovieResultResponse.toDomain(): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        genres = genres,
        releaseDate = releaseDate
    )
