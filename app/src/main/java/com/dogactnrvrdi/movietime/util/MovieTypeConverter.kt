package com.dogactnrvrdi.movietime.util

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MovieTypeConverter {

    @TypeConverter
    fun fromAnyToString(attr: Any?): String {
        if (attr == null)
            return ""
        return attr as String
    }

    @TypeConverter
    fun fromStringToAny(attr: String?): Any {
        if (attr == null)
            return ""
        return attr
    }
}