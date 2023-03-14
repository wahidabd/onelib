package com.wahidabd.onelibrary.domain.movie.mapper

import com.wahidabd.onelibrary.domain.movie.model.Genre
import com.wahidabd.onelibrary.data.movie.model.GenreResultResponse

fun GenreResultResponse.toDomain(): Genre = Genre(id, name)