package com.dogactnrvrdi.movietime.presentation.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.data.model.TvSeries
import com.dogactnrvrdi.movietime.domain.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    private val language = Locale.getDefault().language

    fun getMovieDetails(id: String) {
        viewModelScope.launch {
            repo.getMovieDetails(id = id, language = language).let { movie ->
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