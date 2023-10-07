package com.dogactnrvrdi.movietime.view.home_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.model.toprated.TopRatedMovies
import com.dogactnrvrdi.movietime.repo.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: IMovieRepository
) : ViewModel() {

    // Top Rated Movies
    private val _topRated = MutableLiveData<TopRatedMovies>()
    val topRated: LiveData<TopRatedMovies> get() = _topRated

    init {
        getTopRatedMovies()
    }

    // Top Rated Movies
    private fun getTopRatedMovies() {
        viewModelScope.launch {
            repo.getTopRatedMovies().let { result ->
                _topRated.value = result
            }
        }
    }
}