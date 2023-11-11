package com.dogactnrvrdi.movietime.common

object Constants {
    const val API_KEY = "d642e2709ecc36a4ad8f19a31cedabd5"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

    // Movies
    const val TRENDING_MOVIE_DAY = "trending/movie/day"
    const val MOVIE_NOW_PLAYING = "movie/now_playing"
    const val MOVIE_POPULAR = "movie/popular"
    const val MOVIE_UPCOMING = "movie/upcoming"
    const val MOVIE_DETAILS = "movie/{id}"
    const val SEARCH_MOVIE = "search/movie"

    // TV Series
    const val TRENDING_TV_SERIES_DAY = "trending/tv/day"
    const val TV_AIRING_TODAY = "tv/airing_today"
    const val TV_ON_THE_AIR = "tv/on_the_air"
    const val TV_POPULAR = "tv/popular"
    const val TV_SERIES_DETAILS = "tv/{id}"
    const val SEARCH_TV_SERIES = "search/tv"
}