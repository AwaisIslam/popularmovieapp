package com.ak.popularmovie.model

import com.google.gson.annotations.SerializedName
import com.ak.popularmovie.utils.Constants.IMAGE_BASE_URL

data class Movie(
    val id : Int,
    val title : String,
    @field:SerializedName("poster_path")
    val posterPath : String?,
    @field:SerializedName("release_date")
    val releaseDate : String,
    @field:SerializedName("vote_average")
    val voteAverage : Float
) {

    val posterUrl : String?
        get() = if (posterPath != null) "$IMAGE_BASE_URL$posterPath" else null

}