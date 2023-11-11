package com.dogactnrvrdi.movietime.data.model.now_playing

import com.dogactnrvrdi.movietime.data.model.Movie
import com.google.gson.annotations.SerializedName

data class NowPlayingMovies(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)