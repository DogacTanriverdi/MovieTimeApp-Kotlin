package com.dogactnrvrdi.movietime.data.model

import com.google.gson.annotations.SerializedName

data class TvSeries(
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val popularity: Double
)