package com.dogactnrvrdi.movietime.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.domain.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    // Movie Details
    private var _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun getMovieDetails(id: String) {
        viewModelScope.launch {
            repo.getMovieDetails(id).let { movie ->
                _movie.value = movie
            }
        }
    }

    fun insertMovie() =
        viewModelScope.launch {
            movie.value?.let { movie ->
                repo.insertMovie(movie)
            }
        }
}