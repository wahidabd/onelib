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
//
//fun MovieDetail.toFavoriteDomain(): Favorite =
//    Favorite(
//        id = id!!,
//        title = title.toString(),
//        poster = AppConstants.URL_IMAGE_ORIGINAL + backdropPath.toString(),
//        release = releaseDate.toString().removeRange(3, 9),
//        genres = genres?.map { it.name.toString() + " " }.toString()
//            .replace("[", "")
//            .replace("]", "")
//    )