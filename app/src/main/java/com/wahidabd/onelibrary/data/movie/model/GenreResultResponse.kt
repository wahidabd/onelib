package com.wahidabd.onelibrary.data.movie.model

import com.google.gson.annotations.SerializedName

data class GenreResultResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)
