package com.dogactnrvrdi.movietime.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("movie_details")
data class Movie(
    @PrimaryKey val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    val homepage: String?,
    val overview: String?,
    val status: String?,
    val tagline: String?,
    val title: String?
)
