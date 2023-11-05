package com.dogactnrvrdi.movietime.domain.repo

import androidx.lifecycle.LiveData
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.data.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.data.model.search.SearchMovies
import com.dogactnrvrdi.movietime.data.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.data.model.upcoming.UpcomingMovies

interface MovieRepository {

    suspend fun getTopRatedMovies(language: String): TopRatedMovies

    suspend fun getPopularMovies(language: String): PopularMovies

    suspend fun getUpcomingMovies(language: String): UpcomingMovies

    suspend fun getMovieDetails(id: String, language: String): Movie

    suspend fun searchMovies(searchQuery: String, language: String): SearchMovies

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    fun getAllMovies(): LiveData<List<Movie>>
}