package com.dogactnrvrdi.movietime.data.source.remote

import com.dogactnrvrdi.movietime.data.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.data.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.data.model.upcoming.UpcomingMovies
import com.dogactnrvrdi.movietime.common.Constants.API_KEY
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.data.model.search.SearchMovies
import com.dogactnrvrdi.movietime.data.model.tv_popular.PopularTvSeries
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): TopRatedMovies

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): PopularMovies

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): UpcomingMovies

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: String,
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): Movie

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") searchQuery: String,
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): SearchMovies

    @GET("tv/popular")
    suspend fun getPopularTvSeries(
        @Query("language") language: String = "en",
        @Query("api_key") apiKey: String = API_KEY
    ): PopularTvSeries
}