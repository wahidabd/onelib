package com.wahidabd.onelibrary.data.movie.model

import com.google.gson.annotations.SerializedName


data class MovieResultResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("genre_ids")
    val genres: List<Int>?
)
