package com.dogactnrvrdi.movietime.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.data.model.TvSeries

@Database([Movie::class, TvSeries::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}