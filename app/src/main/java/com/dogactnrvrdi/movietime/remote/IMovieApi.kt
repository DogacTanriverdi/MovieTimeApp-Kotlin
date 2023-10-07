package com.dogactnrvrdi.movietime.remote

import com.dogactnrvrdi.movietime.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieApi {
    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): TopRatedMovies

}