package com.dogactnrvrdi.movietime.data.model

import com.google.gson.annotations.SerializedName

data class TvSeries(
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("last_air_date") val lastAirDate: String,
    @SerializedName("number_of_episodes") val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons") val numberOfSeasons: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    val homepage: String,
    val id: Int,
    val name: String,
    val overview: String,
    val popularity: Double,
    val status: String,
    val tagline: String,
    val type: String
)