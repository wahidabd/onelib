package com.wahidabd.onelibrary.domain.movie.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("genres")
    val genres: List<Genre>? = mutableListOf(),
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("runtime")
    val runtime: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
)