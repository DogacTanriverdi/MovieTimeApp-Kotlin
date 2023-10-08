package com.dogactnrvrdi.movietime.remote

import com.dogactnrvrdi.movietime.model.movie_details.MovieDetails
import com.dogactnrvrdi.movietime.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.model.upcoming.UpcomingMovies
import com.dogactnrvrdi.movietime.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMovieApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): TopRatedMovies

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY
    ) : PopularMovies

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY
    ) : UpcomingMovies

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = API_KEY
    ) : MovieDetails

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") searchQuery: String,
        @Query("api_key") apiKey: String = API_KEY
    ) : MovieDetails
}