package com.wahidabd.onelibrary.domain.movie.mapper

import com.wahidabd.onelibrary.domain.movie.model.Cast
import com.wahidabd.onelibrary.data.movie.model.CastResultResponse
import com.wahidabd.onelibrary.utils.AppConstants

fun CastResultResponse.toDomain(): Cast =
    Cast(name, profilePath = AppConstants.URL_IMAGE_ORIGINAL + profilePath)