package com.dogactnrvrdi.movietime.model.upcoming

data class UpcomingMovies(
    val upcomingDates: UpcomingDates,
    val page: Int,
    val upcomingResults: List<UpcomingResult>,
    val total_pages: Int,
    val total_results: Int
)