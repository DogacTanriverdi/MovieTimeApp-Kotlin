package com.dogactnrvrdi.movietime.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("movie_details")
data class Movie(
    @SerializedName("backdrop_path") val backdropPath: String?,
    val homepage: String?,
    @PrimaryKey val id: Int,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    val status: String?,
    val tagline: String?,
    val title: String?
)
