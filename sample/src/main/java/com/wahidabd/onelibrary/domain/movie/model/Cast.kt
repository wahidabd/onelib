package com.wahidabd.onelibrary.domain.movie.model

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String? = ""
)
