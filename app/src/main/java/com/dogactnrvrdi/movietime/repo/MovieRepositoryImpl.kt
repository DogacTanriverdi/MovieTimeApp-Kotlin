package com.dogactnrvrdi.movietime.repo

import com.dogactnrvrdi.movietime.model.movie_details.MovieDetails
import com.dogactnrvrdi.movietime.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.model.upcoming.UpcomingMovies
import com.dogactnrvrdi.movietime.remote.IMovieApi
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: IMovieApi
) : IMovieRepository {
    override suspend fun getTopRatedMovies(): TopRatedMovies = api.getTopRatedMovies()
    override suspend fun getPopularMovies(): PopularMovies = api.getPopularMovies()
    override suspend fun getUpcomingMovies(): UpcomingMovies = api.getUpcomingMovies()
    override suspend fun getMovieDetails(id: String): MovieDetails = api.getMovieDetails(id)
}