package com.wahidabd.onelibrary.data.movie.model

import com.google.gson.annotations.SerializedName
import com.wahidabd.onelibrary.data.movie.model.GenreResultResponse

data class MovieDetailResultResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genres")
    val genres: List<GenreResultResponse>?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("title")
    val title: String?,
)