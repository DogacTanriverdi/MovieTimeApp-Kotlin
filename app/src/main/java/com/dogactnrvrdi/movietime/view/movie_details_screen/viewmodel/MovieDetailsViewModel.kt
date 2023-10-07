package com.dogactnrvrdi.movietime.view.movie_details_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.model.movie_details.MovieDetails
import com.dogactnrvrdi.movietime.repo.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repo: IMovieRepository
) : ViewModel() {

    // Movie Details
    private var _movie = MutableLiveData<MovieDetails>()
    val movie: LiveData<MovieDetails> get() = _movie

    fun getMovieDetails(id: String) {
        viewModelScope.launch {
            repo.getMovieDetails(id).let { movie ->
                _movie.value = movie
            }
        }
    }
}