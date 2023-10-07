package com.dogactnrvrdi.movietime.repo

import com.dogactnrvrdi.movietime.model.toprated.TopRatedMovies

interface IMovieRepository {
    suspend fun getTopRatedMovies(): TopRatedMovies
}