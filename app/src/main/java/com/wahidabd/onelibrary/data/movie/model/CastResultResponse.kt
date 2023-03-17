package com.wahidabd.onelibrary.data.movie.model

import com.google.gson.annotations.SerializedName

data class CastResultResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String?
)
