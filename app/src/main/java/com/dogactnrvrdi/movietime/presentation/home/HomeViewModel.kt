package com.dogactnrvrdi.movietime.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.data.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.data.model.now_playing.NowPlayingMovies
import com.dogactnrvrdi.movietime.data.model.trending.TrendingMoviesDay
import com.dogactnrvrdi.movietime.data.model.tv_airing_today.AiringTodayTvSeries
import com.dogactnrvrdi.movietime.data.model.tv_on_air.OnTheAirTvSeries
import com.dogactnrvrdi.movietime.data.model.tv_popular.PopularTvSeries
import com.dogactnrvrdi.movietime.data.model.tv_trending.TrendingTvSeriesDay
import com.dogactnrvrdi.movietime.data.model.upcoming.UpcomingMovies
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

    private val _trendingMoviesDay = MutableLiveData<TrendingMoviesDay>()
    val trendingMoviesDay: LiveData<TrendingMoviesDay> get() = _trendingMoviesDay

    private val _nowPlayingMovies = MutableLiveData<NowPlayingMovies>()
    val nowPlayingMovies: LiveData<NowPlayingMovies> get() = _nowPlayingMovies

    private val _popularMovies = MutableLiveData<PopularMovies>()
    val popularMovies: LiveData<PopularMovies> get() = _popularMovies

    private val _upcomingMovies = MutableLiveData<UpcomingMovies>()
    val upcomingMovies: LiveData<UpcomingMovies> get() = _upcomingMovies

    /* TV SERIES */

    private val _trendingTvSeriesDay = MutableLiveData<TrendingTvSeriesDay>()
    val trendingTvSeriesDay: LiveData<TrendingTvSeriesDay> get() = _trendingTvSeriesDay

    private val _airingTodayTvSeries = MutableLiveData<AiringTodayTvSeries>()
    val airingTodayTvSeries: LiveData<AiringTodayTvSeries> get() = _airingTodayTvSeries

    private val _onTheAirTvSeries = MutableLiveData<OnTheAirTvSeries>()
    val onTheAirTvSeries: LiveData<OnTheAirTvSeries> get() = _onTheAirTvSeries

    private val _popularTvSeries = MutableLiveData<PopularTvSeries>()
    val popularTvSeries: LiveData<PopularTvSeries> get() = _popularTvSeries

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
            _nowPlayingMovies.value = nowPlayingMovies
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
            _airingTodayTvSeries.value = airingTodayTvSeries
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