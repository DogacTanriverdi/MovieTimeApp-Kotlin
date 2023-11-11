package com.dogactnrvrdi.movietime.domain.repo

import androidx.lifecycle.LiveData
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.data.model.TvSeries
import com.dogactnrvrdi.movietime.data.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.data.model.search.SearchMovies
import com.dogactnrvrdi.movietime.data.model.now_playing.NowPlayingMovies
import com.dogactnrvrdi.movietime.data.model.trending.TrendingMoviesDay
import com.dogactnrvrdi.movietime.data.model.tv_airing_today.AiringTodayTvSeries
import com.dogactnrvrdi.movietime.data.model.tv_on_air.OnTheAirTvSeries
import com.dogactnrvrdi.movietime.data.model.tv_popular.PopularTvSeries
import com.dogactnrvrdi.movietime.data.model.tv_search.SearchTvSeries
import com.dogactnrvrdi.movietime.data.model.tv_trending.TrendingTvSeriesDay
import com.dogactnrvrdi.movietime.data.model.upcoming.UpcomingMovies

interface MovieRepository {

    /* MOVIES */

    suspend fun getTrendingMoviesDay(language: String): TrendingMoviesDay

    suspend fun getNowPlayingMovies(language: String): NowPlayingMovies

    suspend fun getPopularMovies(language: String): PopularMovies

    suspend fun getUpcomingMovies(language: String): UpcomingMovies

    suspend fun getMovieDetails(id: String, language: String): Movie

    suspend fun searchMovies(searchQuery: String, language: String): SearchMovies

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    fun getAllMovies(): LiveData<List<Movie>>

    /* TV SERIES */

    suspend fun getTrendingTvSeriesDay(language: String): TrendingTvSeriesDay

    suspend fun getAiringTodayTvSeries(language: String): AiringTodayTvSeries

    suspend fun getOnTheAirTvSeries(language: String): OnTheAirTvSeries

    suspend fun getPopularTvSeries(language: String): PopularTvSeries

    suspend fun getTvSeriesDetails(id: String, language: String): TvSeries

    suspend fun searchTvSeries(searchQuery: String, language: String): SearchTvSeries

    suspend fun insertTvSeries(tvSeries: TvSeries)

    suspend fun deleteTvSeries(tvSeries: TvSeries)

    fun getAllTvSeries(): LiveData<List<TvSeries>>
}