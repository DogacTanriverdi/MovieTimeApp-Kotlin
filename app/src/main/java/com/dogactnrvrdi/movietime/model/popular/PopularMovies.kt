package com.dogactnrvrdi.movietime.model.popular

data class PopularMovies(
    val page: Int,
    val popularResults: List<PopularResult>,
    val total_pages: Int,
    val total_results: Int
)