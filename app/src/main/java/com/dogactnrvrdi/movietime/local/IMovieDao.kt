package com.dogactnrvrdi.movietime.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dogactnrvrdi.movietime.model.movie_details.MovieDetails

@Dao
interface IMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieDetails)

    @Delete
    fun deleteMovie(movie: MovieDetails)

    @Query("SELECT * FROM movie_details")
    fun getAllMovies(): LiveData<List<MovieDetails>>
}