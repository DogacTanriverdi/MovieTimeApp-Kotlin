package com.dogactnrvrdi.movietime.domain.repo

import androidx.lifecycle.LiveData
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.data.model.TvSeries
import com.dogactnrvrdi.movietime.data.model.TvSeriesList
import com.dogactnrvrdi.movietime.data.model.MovieList

interface MovieRepository {

    /* MOVIES */

    suspend fun getTrendingMoviesDay(language: String): MovieList

    suspend fun getNowPlayingMovies(language: String): MovieList

    suspend fun getPopularMovies(language: String): MovieList

    suspend fun getUpcomingMovies(language: String): MovieList

    suspend fun getMovieDetails(id: String, language: String): Movie

    suspend fun searchMovies(searchQuery: String, language: String): MovieList

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    fun getAllMovies(): LiveData<List<Movie>>

    /* TV SERIES */

    suspend fun getTrendingTvSeriesDay(language: String): TvSeriesList

    suspend fun getAiringTodayTvSeries(language: String): TvSeriesList

    suspend fun getOnTheAirTvSeries(language: String): TvSeriesList

    suspend fun getPopularTvSeries(language: String): TvSeriesList

    suspend fun getTvSeriesDetails(id: String, language: String): TvSeries

    suspend fun searchTvSeries(searchQuery: String, language: String): TvSeriesList

    suspend fun insertTvSeries(tvSeries: TvSeries)

    suspend fun deleteTvSeries(tvSeries: TvSeries)

    fun getAllTvSeries(): LiveData<List<TvSeries>>
}