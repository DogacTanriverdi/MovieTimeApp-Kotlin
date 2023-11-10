package com.dogactnrvrdi.movietime.data.repo

import androidx.lifecycle.LiveData
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.data.model.TvSeries
import com.dogactnrvrdi.movietime.data.source.local.MovieDao
import com.dogactnrvrdi.movietime.data.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.data.model.search.SearchMovies
import com.dogactnrvrdi.movietime.data.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.data.model.tv_popular.PopularTvSeries
import com.dogactnrvrdi.movietime.data.model.upcoming.UpcomingMovies
import com.dogactnrvrdi.movietime.data.source.remote.MovieApi
import com.dogactnrvrdi.movietime.domain.repo.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val dao: MovieDao
) : MovieRepository {

    override suspend fun getTopRatedMovies(language: String): TopRatedMovies =
        api.getTopRatedMovies(language = language)

    override suspend fun getPopularMovies(language: String): PopularMovies =
        api.getPopularMovies(language = language)

    override suspend fun getUpcomingMovies(language: String): UpcomingMovies =
        api.getUpcomingMovies(language = language)

    override suspend fun getMovieDetails(id: String, language: String): Movie =
        api.getMovieDetails(id = id, language = language)

    override suspend fun searchMovies(searchQuery: String, language: String): SearchMovies =
        api.searchMovies(searchQuery, language = language)

    override suspend fun getPopularTvSeries(language: String): PopularTvSeries =
        api.getPopularTvSeries(language)

    override suspend fun getTvSeriesDetails(id: String, language: String): TvSeries =
        api.getTvSeriesDetails(id = id, language = language)

    override suspend fun insertMovie(movie: Movie) = dao.insertMovie(movie)

    override suspend fun deleteMovie(movie: Movie) = dao.deleteMovie(movie)

    override fun getAllMovies(): LiveData<List<Movie>> = dao.getAllMovies()

    override suspend fun insertTvSeries(tvSeries: TvSeries) = dao.insertTvSeries(tvSeries)

    override suspend fun deleteTvSeries(tvSeries: TvSeries) = dao.deleteTvSeries(tvSeries)

    override fun getAllTvSeries(): LiveData<List<TvSeries>> = dao.getAllTvSeries()
}