package com.dogactnrvrdi.movietime.model.toprated

data class TopRatedMovies(
    val page: Int,
    val results: List<TopRatedResult>,
    val total_pages: Int,
    val total_results: Int
)