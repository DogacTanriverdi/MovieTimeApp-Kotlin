package com.dogactnrvrdi.movietime.repo

import androidx.lifecycle.LiveData
import com.dogactnrvrdi.movietime.local.IMovieDao
import com.dogactnrvrdi.movietime.model.movie_details.MovieDetails
import com.dogactnrvrdi.movietime.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.model.upcoming.UpcomingMovies
import com.dogactnrvrdi.movietime.remote.IMovieApi
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: IMovieApi,
    private val dao: IMovieDao
) : IMovieRepository {
    override suspend fun getTopRatedMovies(): TopRatedMovies = api.getTopRatedMovies()
    override suspend fun getPopularMovies(): PopularMovies = api.getPopularMovies()
    override suspend fun getUpcomingMovies(): UpcomingMovies = api.getUpcomingMovies()
    override suspend fun getMovieDetails(id: String): MovieDetails = api.getMovieDetails(id)
    override suspend fun searchMovies(searchQuery: String): MovieDetails =
        api.searchMovies(searchQuery)

    override suspend fun insertMovie(movie: MovieDetails) = dao.insertMovie(movie)

    override suspend fun deleteMovie(movie: MovieDetails) = dao.deleteMovie(movie)

    override fun getAllMovies(): LiveData<List<MovieDetails>> = dao.getAllMovies()
}