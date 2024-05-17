package com.wahidabd.onelibrary.domain.movie.mapper

import com.wahidabd.onelibrary.domain.movie.model.MovieDetail
import com.wahidabd.onelibrary.data.movie.model.MovieDetailResultResponse
import com.wahidabd.onelibrary.utils.AppConstants

fun MovieDetailResultResponse.toDomain(): MovieDetail =
    MovieDetail(
        id = id,
        posterPath = AppConstants.URL_IMAGE_ORIGINAL + posterPath,
        backdropPath = AppConstants.URL_IMAGE_ORIGINAL + backdropPath,
        overview = overview,
        title = title,
        releaseDate = releaseDate,
        genres = genres?.map { it.toDomain() }
    )