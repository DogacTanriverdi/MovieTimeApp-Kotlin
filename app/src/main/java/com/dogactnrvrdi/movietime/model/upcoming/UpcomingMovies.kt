package com.dogactnrvrdi.movietime.model.upcoming

data class UpcomingMovies(
    val dates: UpcomingDates,
    val page: Int,
    val results: List<UpcomingResult>,
    val total_pages: Int,
    val total_results: Int
)