package com.dogactnrvrdi.movietime.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.data.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.data.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.data.model.tv_popular.PopularTvSeries
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

    private val _topRated = MutableLiveData<TopRatedMovies>()
    val topRated: LiveData<TopRatedMovies> get() = _topRated

    private val _popular = MutableLiveData<PopularMovies>()
    val popular: LiveData<PopularMovies> get() = _popular

    private val _upcoming = MutableLiveData<UpcomingMovies>()
    val upcoming: LiveData<UpcomingMovies> get() = _upcoming

    private val _popularTvSeries = MutableLiveData<PopularTvSeries>()
    val popularTvSeries: LiveData<PopularTvSeries> get() = _popularTvSeries

    private val language = Locale.getDefault().language


    init {
        getTopRatedMovies()
        getPopularMovies()
        getUpcomingMovies()
        getPopularTvSeries()
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            repo.getTopRatedMovies(language).let { topRatedMovies ->
                _topRated.value = topRatedMovies
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            repo.getPopularMovies(language).let { popularMovies ->
                _popular.value = popularMovies
            }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            repo.getUpcomingMovies(language).let { upcomingMovies ->
                _upcoming.value = upcomingMovies
            }
        }
    }

    private fun getPopularTvSeries() {
        viewModelScope.launch {
            repo.getPopularTvSeries(language = language).let { popularTvSeries ->
                _popularTvSeries.value = popularTvSeries
            }
        }
    }
}