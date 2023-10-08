package com.dogactnrvrdi.movietime.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dogactnrvrdi.movietime.model.movie_details.MovieDetails
import com.dogactnrvrdi.movietime.util.MovieTypeConverter

@Database([MovieDetails::class], version = 1)
@TypeConverters(MovieTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): IMovieDao
}