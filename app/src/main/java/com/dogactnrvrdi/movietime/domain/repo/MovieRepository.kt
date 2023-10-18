package com.dogactnrvrdi.movietime.domain.repo

import androidx.lifecycle.LiveData
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.data.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.data.model.search.SearchMovies
import com.dogactnrvrdi.movietime.data.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.data.model.upcoming.UpcomingMovies

interface MovieRepository {
    suspend fun getTopRatedMovies(): TopRatedMovies
    suspend fun getPopularMovies(): PopularMovies
    suspend fun getUpcomingMovies(): UpcomingMovies
    suspend fun getMovieDetails(id: String): Movie
    suspend fun searchMovies(searchQuery: String): SearchMovies
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
    fun getAllMovies(): LiveData<List<Movie>>
}