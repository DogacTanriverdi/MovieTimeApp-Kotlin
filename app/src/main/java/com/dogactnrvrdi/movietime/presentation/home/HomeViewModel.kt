package com.dogactnrvrdi.movietime.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.data.model.TvSeriesList
import com.dogactnrvrdi.movietime.data.model.MovieList
import com.dogactnrvrdi.movietime.domain.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    private val language = Locale.getDefault().language

    /* MOVIES */

    private val _trendingMoviesDay = MutableLiveData<MovieList>()
    val trendingMoviesDay: LiveData<MovieList> get() = _trendingMoviesDay

    private val _movieList = MutableLiveData<MovieList>()
    val movieList: LiveData<MovieList> get() = _movieList

    private val _popularMovies = MutableLiveData<MovieList>()
    val popularMovies: LiveData<MovieList> get() = _popularMovies

    private val _upcomingMovies = MutableLiveData<MovieList>()
    val upcomingMovies: LiveData<MovieList> get() = _upcomingMovies

    /* TV SERIES */

    private val _trendingTvSeriesDay = MutableLiveData<TvSeriesList>()
    val trendingTvSeriesDay: LiveData<TvSeriesList> get() = _trendingTvSeriesDay

    private val _tvSeriesList = MutableLiveData<TvSeriesList>()
    val tvSeriesList: LiveData<TvSeriesList> get() = _tvSeriesList

    private val _onTheAirTvSeries = MutableLiveData<TvSeriesList>()
    val onTheAirTvSeries: LiveData<TvSeriesList> get() = _onTheAirTvSeries

    private val _popularTvSeries = MutableLiveData<TvSeriesList>()
    val popularTvSeries: LiveData<TvSeriesList> get() = _popularTvSeries

    init {
        getTrendingMoviesDay()
        getNowPlayingMovies()
        getPopularMovies()
        getUpcomingMovies()

        getTrendingTvSeriesDay()
        getAiringTodayTvSeries()
        getOnTheAirTvSeries()
        getPopularTvSeries()
    }

    /* MOVIES */

    private fun getTrendingMoviesDay() = viewModelScope.launch {
        repo.getTrendingMoviesDay(language).let { trendingMoviesDay ->
            _trendingMoviesDay.value = trendingMoviesDay
        }
    }

    private fun getNowPlayingMovies() = viewModelScope.launch {
        repo.getNowPlayingMovies(language).let { nowPlayingMovies ->
            _movieList.value = nowPlayingMovies
        }
    }

    private fun getPopularMovies() = viewModelScope.launch {
        repo.getPopularMovies(language).let { popularMovies ->
            _popularMovies.value = popularMovies
        }
    }

    private fun getUpcomingMovies() = viewModelScope.launch {
        repo.getUpcomingMovies(language).let { upcomingMovies ->
            _upcomingMovies.value = upcomingMovies
        }
    }

    /* TV SERIES */

    private fun getTrendingTvSeriesDay() = viewModelScope.launch {
        repo.getTrendingTvSeriesDay(language).let { trendingTvSeries ->
            _trendingTvSeriesDay.value = trendingTvSeries
        }
    }

    private fun getAiringTodayTvSeries() = viewModelScope.launch {
        repo.getAiringTodayTvSeries(language).let { airingTodayTvSeries ->
            _tvSeriesList.value = airingTodayTvSeries
        }
    }

    private fun getOnTheAirTvSeries() = viewModelScope.launch {
        repo.getOnTheAirTvSeries(language).let { onTheAirTvSeries ->
            _onTheAirTvSeries.value = onTheAirTvSeries
        }
    }

    private fun getPopularTvSeries() = viewModelScope.launch {
        repo.getPopularTvSeries(language).let { popularTvSeries ->
            _popularTvSeries.value = popularTvSeries
        }
    }
}