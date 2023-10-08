package com.dogactnrvrdi.movietime.repo

import com.dogactnrvrdi.movietime.model.movie_details.MovieDetails
import com.dogactnrvrdi.movietime.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.model.upcoming.UpcomingMovies

interface IMovieRepository {
    suspend fun getTopRatedMovies(): TopRatedMovies
    suspend fun getPopularMovies(): PopularMovies
    suspend fun getUpcomingMovies(): UpcomingMovies
    suspend fun getMovieDetails(id: String): MovieDetails
    suspend fun searchMovies(searchQuery: String): MovieDetails
}