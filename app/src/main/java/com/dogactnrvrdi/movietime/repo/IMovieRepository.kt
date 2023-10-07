package com.dogactnrvrdi.movietime.repo

import com.dogactnrvrdi.movietime.model.movie_details.MovieDetails
import com.dogactnrvrdi.movietime.model.toprated.TopRatedMovies

interface IMovieRepository {
    suspend fun getTopRatedMovies(): TopRatedMovies
    suspend fun getMovieDetails(id: String): MovieDetails
}