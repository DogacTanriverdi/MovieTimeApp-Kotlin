package com.dogactnrvrdi.movietime.data.model.search

import com.dogactnrvrdi.movietime.data.model.Movie
import com.google.gson.annotations.SerializedName

data class SearchMovies(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
