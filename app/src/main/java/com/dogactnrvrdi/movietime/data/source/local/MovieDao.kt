package com.dogactnrvrdi.movietime.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.data.model.TvSeries

@Dao
interface MovieDao {

    /* Movies */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movie_details")
    fun getAllMovies(): LiveData<List<Movie>>

    /* TV Series */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvSeries(tvSeries: TvSeries)

    @Delete
    fun deleteTvSeries(tvSeries: TvSeries)

    @Query("SELECT * FROM tv_series_details")
    fun getAllTvSeries(): LiveData<List<TvSeries>>
}