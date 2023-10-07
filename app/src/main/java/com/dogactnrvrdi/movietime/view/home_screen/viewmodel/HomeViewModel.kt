package com.dogactnrvrdi.movietime.view.home_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.model.popular.PopularMovies
import com.dogactnrvrdi.movietime.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.model.upcoming.UpcomingMovies
import com.dogactnrvrdi.movietime.repo.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: IMovieRepository
) : ViewModel() {

    // Top Rated Movies
    private var _topRated = MutableLiveData<TopRatedMovies>()
    val topRated: LiveData<TopRatedMovies> get() = _topRated

    // Popular Movies
    private var _popular = MutableLiveData<PopularMovies>()
    val popular: LiveData<PopularMovies> get() = _popular

    // Upcoming Movies
    private var _upcoming = MutableLiveData<UpcomingMovies>()
    val upcoming: LiveData<UpcomingMovies> get() = _upcoming


    init {
        getTopRatedMovies()
        getPopularMovies()
        getUpcomingMovies()
    }

    // Top Rated Movies
    private fun getTopRatedMovies() {
        viewModelScope.launch {
            repo.getTopRatedMovies().let { topRatedMovies ->
                _topRated.value = topRatedMovies
            }
        }
    }

    // Popular Movies
    private fun getPopularMovies() {
        viewModelScope.launch {
            repo.getPopularMovies().let { popularMovies ->
                _popular.value = popularMovies
            }
        }
    }

    // Upcoming Movies
    private fun getUpcomingMovies() {
        viewModelScope.launch {
            repo.getUpcomingMovies().let { upcomingMovies ->
                _upcoming.value = upcomingMovies
            }
        }
    }
}