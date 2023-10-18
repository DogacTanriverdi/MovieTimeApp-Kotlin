package com.dogactnrvrdi.movietime.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dogactnrvrdi.movietime.data.model.Movie

@Database([Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): IMovieDao
}