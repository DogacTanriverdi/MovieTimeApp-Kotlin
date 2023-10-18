package com.dogactnrvrdi.movietime.data.repo

import androidx.lifecycle.LiveData
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.data.source.local.IMovieDao
import com.dogactnrvrdi.movietime.data.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.data.model.search.SearchMovies
import com.dogactnrvrdi.movietime.data.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.data.model.upcoming.UpcomingMovies
import com.dogactnrvrdi.movietime.data.source.remote.MovieApi
import com.dogactnrvrdi.movietime.domain.repo.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val dao: IMovieDao
) : MovieRepository {
    override suspend fun getTopRatedMovies(): TopRatedMovies = api.getTopRatedMovies()
    override suspend fun getPopularMovies(): PopularMovies = api.getPopularMovies()
    override suspend fun getUpcomingMovies(): UpcomingMovies = api.getUpcomingMovies()
    override suspend fun getMovieDetails(id: String): Movie = api.getMovieDetails(id)
    override suspend fun searchMovies(searchQuery: String): SearchMovies =
        api.searchMovies(searchQuery)

    override suspend fun insertMovie(movie: Movie) = dao.insertMovie(movie)

    override suspend fun deleteMovie(movie: Movie) = dao.deleteMovie(movie)

    override fun getAllMovies(): LiveData<List<Movie>> = dao.getAllMovies()
}