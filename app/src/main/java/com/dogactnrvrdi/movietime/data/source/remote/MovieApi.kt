package com.dogactnrvrdi.movietime.data.source.remote

import com.dogactnrvrdi.movietime.common.Constants.API_KEY
import com.dogactnrvrdi.movietime.common.Constants.MOVIE_DETAILS
import com.dogactnrvrdi.movietime.common.Constants.MOVIE_NOW_PLAYING
import com.dogactnrvrdi.movietime.common.Constants.MOVIE_POPULAR
import com.dogactnrvrdi.movietime.common.Constants.MOVIE_UPCOMING
import com.dogactnrvrdi.movietime.common.Constants.SEARCH_MOVIE
import com.dogactnrvrdi.movietime.common.Constants.SEARCH_TV_SERIES
import com.dogactnrvrdi.movietime.common.Constants.TRENDING_MOVIE_DAY
import com.dogactnrvrdi.movietime.common.Constants.TRENDING_TV_SERIES_DAY
import com.dogactnrvrdi.movietime.common.Constants.TV_AIRING_TODAY
import com.dogactnrvrdi.movietime.common.Constants.TV_ON_THE_AIR
import com.dogactnrvrdi.movietime.common.Constants.TV_POPULAR
import com.dogactnrvrdi.movietime.common.Constants.TV_SERIES_DETAILS
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.data.model.TvSeries
import com.dogactnrvrdi.movietime.data.model.TvSeriesList
import com.dogactnrvrdi.movietime.data.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    /* MOVIES */

    @GET(TRENDING_MOVIE_DAY)
    suspend fun getTrendingMoviesDay(
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieList

    @GET(MOVIE_NOW_PLAYING)
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieList

    @GET(MOVIE_POPULAR)
    suspend fun getPopularMovies(
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieList

    @GET(MOVIE_UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieList

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("id") id: String,
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): Movie

    @GET(SEARCH_MOVIE)
    suspend fun searchMovies(
        @Query("query") searchQuery: String,
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieList

    /* TV SERIES */

    @GET(TRENDING_TV_SERIES_DAY)
    suspend fun getTrendingTvSeriesDay(
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): TvSeriesList

    @GET(TV_AIRING_TODAY)
    suspend fun getAiringTodayTvSeries(
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): TvSeriesList

    @GET(TV_ON_THE_AIR)
    suspend fun getOnTheAirTvSeries(
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): TvSeriesList

    @GET(TV_POPULAR)
    suspend fun getPopularTvSeries(
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): TvSeriesList

    @GET(TV_SERIES_DETAILS)
    suspend fun getTvSeriesDetails(
        @Path("id") id: String,
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): TvSeries

    @GET(SEARCH_TV_SERIES)
    suspend fun searchTvSeries(
        @Query("query") searchQuery: String,
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): TvSeriesList
}