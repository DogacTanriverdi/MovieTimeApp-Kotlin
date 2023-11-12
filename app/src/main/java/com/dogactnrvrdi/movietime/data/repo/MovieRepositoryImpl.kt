package com.dogactnrvrdi.movietime.data.repo

import androidx.lifecycle.LiveData
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.data.model.TvSeries
import com.dogactnrvrdi.movietime.data.source.local.MovieDao
import com.dogactnrvrdi.movietime.data.model.TvSeriesList
import com.dogactnrvrdi.movietime.data.model.MovieList
import com.dogactnrvrdi.movietime.data.source.remote.MovieApi
import com.dogactnrvrdi.movietime.domain.repo.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val dao: MovieDao
) : MovieRepository {

    /* MOVIES */

    override suspend fun getTrendingMoviesDay(language: String): MovieList =
        api.getTrendingMoviesDay(language)

    override suspend fun getNowPlayingMovies(language: String): MovieList =
        api.getNowPlayingMovies(language)

    override suspend fun getPopularMovies(language: String): MovieList =
        api.getPopularMovies(language)

    override suspend fun getUpcomingMovies(language: String): MovieList =
        api.getUpcomingMovies(language)

    override suspend fun getMovieDetails(id: String, language: String): Movie =
        api.getMovieDetails(id, language)

    override suspend fun searchMovies(searchQuery: String, language: String): MovieList =
        api.searchMovies(searchQuery, language)

    override suspend fun insertMovie(movie: Movie) = dao.insertMovie(movie)

    override suspend fun deleteMovie(movie: Movie) = dao.deleteMovie(movie)

    override fun getAllMovies(): LiveData<List<Movie>> = dao.getAllMovies()

    /* TV SERIES */

    override suspend fun getTrendingTvSeriesDay(language: String): TvSeriesList =
        api.getTrendingTvSeriesDay(language)

    override suspend fun getAiringTodayTvSeries(language: String): TvSeriesList =
        api.getAiringTodayTvSeries(language)

    override suspend fun getOnTheAirTvSeries(language: String): TvSeriesList =
        api.getOnTheAirTvSeries(language)

    override suspend fun getPopularTvSeries(language: String): TvSeriesList =
        api.getPopularTvSeries(language)

    override suspend fun getTvSeriesDetails(id: String, language: String): TvSeries =
        api.getTvSeriesDetails(id, language)

    override suspend fun searchTvSeries(searchQuery: String, language: String): TvSeriesList =
        api.searchTvSeries(searchQuery, language)

    override suspend fun insertTvSeries(tvSeries: TvSeries) = dao.insertTvSeries(tvSeries)

    override suspend fun deleteTvSeries(tvSeries: TvSeries) = dao.deleteTvSeries(tvSeries)

    override fun getAllTvSeries(): LiveData<List<TvSeries>> = dao.getAllTvSeries()
}